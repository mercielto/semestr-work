package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostShortMapper {

    PostShortResponse toResponse(PostEntity entity);
}
