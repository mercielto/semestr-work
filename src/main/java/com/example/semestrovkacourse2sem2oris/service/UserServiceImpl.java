package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRegistrationRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.UserExtendedResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.exception.*;
import com.example.semestrovkacourse2sem2oris.mapper.UserMapper;
import com.example.semestrovkacourse2sem2oris.model.Role;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.UserRepository;
import com.example.semestrovkacourse2sem2oris.util.UserParamsChecker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final UserParamsChecker checker;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Value("${image.default}")
    private String defaultImage;

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public UserEntity getCurrent() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(login);
    }

    @Override
    public UserExtendedResponse getByLink(String link) {
        return mapper.toExtendedResponse(getEntityByLink(link));
    }

    @Transactional
    @Override
    public void changeFollowByLink(String link) {
        UserEntity currentUser = getCurrentUser();
        UserEntity followUser = getEntityByLink(link);
        List<UserEntity> following = currentUser.getFollowing();
        List<UserEntity> follower = followUser.getFollowers();

        if (following.contains(followUser)) {
            following.remove(followUser);
            follower.remove(currentUser);
        } else {
            following.add(followUser);
            follower.add(currentUser);
        }

        repository.save(currentUser);
        repository.save(followUser);
    }

    @Override
    public boolean isCurrentSubscribed(String link) {
        UserEntity current = getCurrentUser();
        UserEntity user = getEntityByLink(link);
        return current.getFollowing().contains(user);
    }

    @Override
    public boolean isCurrentUserIsOwner(String link) {
        UserEntity user = getCurrentUser();
        return user.getLink().equals(link);
    }

    @Override
    public void updateUsernameByLink(String link, String username) {
        UserEntity user = getCurrentUser();
        user.setUsername(username);
        repository.save(user);
    }

    @Override
    public void updateBioByLink(String link, String bio) {
        UserEntity user = getCurrentUser();
        user.setBio(bio);
        repository.save(user);
    }

    @Override
    public void updateImage(MultipartFile file, String link) {
        UserEntity user = getEntityByLink(link);
        String contentType = file.getContentType();

        if (contentType == null) {
            throw new MultipartHasNoMimeType(file.getOriginalFilename());
        }

        String[] splitContentType = contentType.split("/");
        if (!splitContentType[0].equals("image")) {
            throw new MultipartHasNotImageException();
        }


        if (!user.getImageName().equals(defaultImage)) {
            Path oldpath = Paths.get(uploadDir + user.getImageName());
            try {
                Files.delete(oldpath);
            } catch (IOException e) {
                throw new NotFoundServiceException(
                        "File to delete not found by link '%s'".formatted(
                                uploadDir + user.getImageName()
                        )
                );
            }
        }

        String fileName = "%s.%s".formatted(user.getLogin(), splitContentType[1]);
        Path path = Paths.get(uploadDir + fileName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            user.setImageName(fileName);
            repository.save(user);
        } catch (IOException e) {
            throw new CouldNotSaveImageException(uploadDir + fileName);
        }
    }

    @Override
    public UserEntity create(UserRegistrationRequest request) {
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
    public UserEntity getEntityByLink(String link) {
        return repository.findByLink(link).orElseThrow(() -> new UserNotFoundException(link));
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
    public boolean check(UserRegistrationRequest request) {
        return checker.check(request);
    }
}
