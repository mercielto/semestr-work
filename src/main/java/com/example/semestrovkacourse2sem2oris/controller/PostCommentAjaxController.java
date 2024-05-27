package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.RestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.request.PostCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostCommentResponse;
import com.example.semestrovkacourse2sem2oris.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post/comment/ajax/")
@RestController
@RestExceptionAnnotation
public class PostCommentAjaxController {

    private final PostCommentService postCommentService;

    @GetMapping("/{link}/{from}/{to}")
    public List<PostCommentResponse> getComments(@PathVariable("link") String link,
                                                 @PathVariable("from") int from,
                                                 @PathVariable("to") Integer to) {
        return postCommentService.getByPostLinkWithPagination(link, from, to);
    }

    @PostMapping("/{link}")
    public PostCommentResponse postComment(@PathVariable("link") String link,
                            @RequestBody PostCommentRequest postCommentRequest) {
        return postCommentService.postCommentByPostLink(link, postCommentRequest);
    }
}
