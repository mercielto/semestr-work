package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.response.BranchResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "rating", expression = "java(entity.getAverageRating())")
    @Mapping(target = "postLink", expression = "java(entity.getPost().getWebLink())")
    BranchResponse toResponse(BranchEntity entity);


    @Mapping(target = "rating", expression = "java(entity.getAverageRating())")
    @Mapping(target = "postLink", expression = "java(entity.getPost().getWebLink())")
    @Mapping(target = "postName", expression = "java(entity.getPost().getTitle())")
    BranchShortResponse toShortResponse(BranchEntity entity);
}
