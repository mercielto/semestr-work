package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;

import java.util.List;

public interface AdminService {

    void changeActivityStatus(String login);

    List<UserResponse> getAllUsers();
}
