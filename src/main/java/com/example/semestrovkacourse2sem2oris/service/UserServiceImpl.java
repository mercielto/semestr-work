package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.exception.UserNotFoundException;
import com.example.semestrovkacourse2sem2oris.mapper.UserMapper;
import com.example.semestrovkacourse2sem2oris.model.Role;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.UserRepository;
import com.example.semestrovkacourse2sem2oris.util.UserParamsChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final UserParamsChecker checker;

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }


    @Override
    public UserEntity create(UserRequest request) {
        UserEntity user = mapper.toEntity(request);
        // TODO: разобраться, как сделать так, чтобы поля active и role генерировались сами
        user.setActive(true);
        user.setRole(Role.USER);
        user.setLink(UUID.randomUUID().toString());
        user.setPassword(encoder.encode(user.getPassword()));

        if (repository.existsByLogin(user.getLogin())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    @Override
    public UserEntity getByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Override
    public UserEntity getCurrentUser() {
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(login);
    }

    @Override
    public void setRole(Role role) {
        UserEntity user = getCurrentUser();
        user.setRole(role);
        repository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public boolean check(UserRequest request) {
        return checker.check(request);
    }
}
