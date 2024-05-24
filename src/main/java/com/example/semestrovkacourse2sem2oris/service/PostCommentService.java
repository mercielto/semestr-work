package com.example.semestrovkacourse2sem2oris.service;

import com.example.semestrovkacourse2sem2oris.dto.request.PostCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostCommentResponse;

import java.util.List;

public interface PostCommentService {

    List<PostCommentResponse> getByPostLinkWithPagination(String link, int from, Integer to);

    PostCommentResponse postCommentByPostLink(String link, PostCommentRequest postCommentRequest);
}
