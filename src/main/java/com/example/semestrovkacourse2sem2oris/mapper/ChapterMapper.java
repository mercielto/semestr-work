package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    @Mapping(target = "branchLink", expression = "java(entity.getBranch().getLink())")
    ChapterResponse toResponse(ChapterEntity entity);

    @Mapping(target = "id", ignore = true)
    ChapterEntity toEntity(ChapterRequest request);
}
