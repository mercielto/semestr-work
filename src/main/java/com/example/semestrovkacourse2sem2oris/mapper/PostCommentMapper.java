package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.request.PostCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostCommentResponse;
import com.example.semestrovkacourse2sem2oris.model.PostCommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostCommentMapper {

    @Mapping(target = "postId", expression = "java( entity.getPost().getPostId() )")
    PostCommentResponse toResponse(PostCommentEntity entity);

    PostCommentEntity toEntity(PostCommentRequest request);
}
