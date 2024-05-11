package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    public void changeActivityStatus(String login) {
        UserEntity user = userService.getByLogin(login);
        user.setActive(!user.isActive());
        userService.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }
}
