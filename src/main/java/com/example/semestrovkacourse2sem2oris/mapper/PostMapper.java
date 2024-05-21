package com.example.semestrovkacourse2sem2oris.mapper;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostUserShortResponse;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.PostStatus;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ChapterMapper.class, PostService.class})
public interface PostMapper {

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "status", qualifiedByName = "stringToPostStatus")
    PostEntity toEntity(PostRequest request);

    @Named("stringToPostStatus")
    default PostStatus stringToPostStatus(String statusString) {
        return PostStatus.valueOf(statusString);
    }

    PostResponse toResponse(PostEntity entity);

    @Mapping(target = "editorsCount", expression = "java( entity.getEditors().size() )")
    @Mapping(target = "branchesCount", expression = "java( entity.getBranches().size() )")
    @Mapping(target = "rating", expression = "java( entity.getAverageRating() )")
    @Mapping(target = "readCount", expression = "java( entity.getReadUsers().size() )")
    PostShortResponse toShortResponse(PostEntity entity);

    @Mapping(target = "response", expression = "java( toShortResponse(entity) )")
    @Mapping(target = "status", expression = "java( postService.isCurrentUserRead(entity) )")
    PostUserShortResponse toPostUserShortResponse(PostEntity entity, @Context PostService postService);
}
