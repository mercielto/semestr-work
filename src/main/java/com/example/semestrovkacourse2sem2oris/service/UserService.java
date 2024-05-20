package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.model.Role;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserEntity create(UserRequest user);

    UserEntity getByLogin(String login);

    UserEntity getCurrentUser();

    void setRole(Role role);

    List<UserResponse> getAll();

    boolean check(UserRequest request);

    UserEntity save(UserEntity user);

    UserEntity getCurrent();
}
