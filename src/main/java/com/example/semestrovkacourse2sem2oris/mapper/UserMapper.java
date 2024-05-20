package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRegistrationRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.UserExtendedResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", ignore = true)
    UserEntity toEntity(UserRegistrationRequest userRegistrationRequest);

    @Mapping(target = "followersCount", expression = "java(entity.getFollowers().size())")
    @Mapping(target = "followingsCount", expression = "java(entity.getFollowing().size())")
    UserResponse toResponse(UserEntity entity);

    @Mapping(target = "followersCount", expression = "java(entity.getFollowers().size())")
    @Mapping(target = "followingsCount", expression = "java(entity.getFollowing().size())")
    UserExtendedResponse toExtendedResponse(UserEntity entity);
}
