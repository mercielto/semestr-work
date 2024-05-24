package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostCommentResponse;
import com.example.semestrovkacourse2sem2oris.mapper.PostCommentMapper;
import com.example.semestrovkacourse2sem2oris.model.PostCommentEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostService postService;
    private final PostCommentMapper mapper;
    private final UserService userService;

    @Override
    public List<PostCommentResponse> getByPostLinkWithPagination(String link, int from, Integer count) {
        PostEntity post = postService.getEntityByLink(link);
        Pageable pageable = PageRequest.of(from, count);
        return postCommentRepository.findAllByPost(post, pageable).stream().map(mapper::toResponse).toList();
    }

    @Override
    public PostCommentResponse postCommentByPostLink(String link, PostCommentRequest postCommentRequest) {
        PostEntity post = postService.getEntityByLink(link);
        UserEntity user = userService.getCurrentUser();
        PostCommentEntity postCommentEntity = mapper.toEntity(postCommentRequest);
        postCommentEntity.setPost(post);
        postCommentEntity.setUser(user);
        postCommentRepository.save(postCommentEntity);
        return mapper.toResponse(postCommentEntity);
    }
}
