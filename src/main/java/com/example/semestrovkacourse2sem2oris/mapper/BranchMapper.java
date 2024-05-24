package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.response.BranchResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchUserShortResponse;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(target = "rating", expression = "java(entity.getAverageRating())")
    @Mapping(target = "postLink", expression = "java(entity.getPost().getWebLink())")
    @Mapping(target = "votersCount", expression = " java( entity.getRatesCount() ) ")
    BranchResponse toResponse(BranchEntity entity);


    @Mapping(target = "rating", expression = "java(entity.getAverageRating())")
    @Mapping(target = "postLink", expression = "java(entity.getPost().getWebLink())")
    @Mapping(target = "postName", expression = "java(entity.getPost().getTitle())")
    @Mapping(target = "votersCount", expression = " java( entity.getRatesCount() ) ")
    BranchShortResponse toShortResponse(BranchEntity entity);

    @Mapping(target = "response", expression = "java( toShortResponse( entity ) )")
    @Mapping(target = "rating", expression = "java( rating )")
    BranchUserShortResponse toBranchUserShortResponse(BranchEntity entity, Integer rating);

}
