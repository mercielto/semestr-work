package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRegistrationRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.UserExtendedResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.model.Role;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {

    UserEntity create(UserRegistrationRequest user);

    UserEntity getByLogin(String login);

    UserEntity getEntityByLink(String link);

    UserEntity getCurrentUser();

    void setRole(Role role);

    List<UserResponse> getAll();

    boolean check(UserRegistrationRequest request);

    UserEntity save(UserEntity user);

    UserEntity getCurrent();

    UserExtendedResponse getByLink(String link);

    void changeFollowByLink(String link);

    boolean isCurrentSubscribed(String link);

    boolean isCurrentUserIsOwner(String link);

    void updateUsernameByLink(String link, String username);

    void updateBioByLink(String link, String bio);

    void updateImage(MultipartFile file, String link);
}
