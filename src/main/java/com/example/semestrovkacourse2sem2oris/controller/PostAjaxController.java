package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.RestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRateRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostReadRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.service.PostCommentService;
import com.example.semestrovkacourse2sem2oris.service.PostRateService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/ajax")
@RestExceptionAnnotation
public class PostAjaxController {

    private final PostService postService;
    private final PostRateService postRateService;
    private final PostCommentService postCommentService;

    @PostMapping(value = "/settings/save/{link}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody PostRequest postRequest,
                       @PathVariable("link") String link) {
        postService.saveChanges(postRequest, link);
    }

    @DeleteMapping("/{link}")
    public void delete(@PathVariable("link") String link) {
        postService.delete(link);
    }

    @PatchMapping("/rate/{link}")
    public void rate(@PathVariable("link") String link,
                     @RequestBody PostRateRequest request) {
        postRateService.rate(link, request);
    }

    @PatchMapping("/read/{link}")
    public void read(@PathVariable("link") String link,
                     @RequestBody PostReadRequest request) {
        postService.changeReadStatus(link, request);
    }

    @GetMapping("/content")
    public List<PostShortResponse> getWithPagination(@RequestParam("count") Integer count,
                                                     @RequestParam("size") Integer size) {
        return postService.getWithPagination(count, size);
    }
}
