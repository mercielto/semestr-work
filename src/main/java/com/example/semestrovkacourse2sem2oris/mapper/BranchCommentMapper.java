package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchCommentResponse;
import com.example.semestrovkacourse2sem2oris.model.BranchCommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchCommentMapper {

    BranchCommentResponse toResponse(BranchCommentEntity entity);

    BranchCommentEntity toEntity(BranchCommentRequest request);
}
