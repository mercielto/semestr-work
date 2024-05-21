package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRateRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostReadRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import com.example.semestrovkacourse2sem2oris.service.PostRateService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/ajax")
public class PostAjaxController {

    private final PostService postService;
    private final PostRateService postRateService;

    @GetMapping("/settings/{link}")
    public String settings(@PathVariable("link") String link,
                           Model model) {
        PostResponse postResponse = postService.getByLink(link);
        model.addAttribute("post", postResponse);
        return "post-create-settings-ajax";
    }

    @GetMapping("/chapters/{link}")
    public String chapters(@PathVariable("link") String link,
                           Model model) {
        PostResponse response = postService.getByLink(link);
        model.addAttribute("post", response);
        return "post-create-chapters-ajax";
    }

    @GetMapping("/chapter-add/{link}")
    public String chapterAdd(@PathVariable("link") String link,
                             Model model) {
        PostResponse response = postService.getByLink(link);
        model.addAttribute("post", response);
        return "post-create-chapter-add-ajax";
    }

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
}
