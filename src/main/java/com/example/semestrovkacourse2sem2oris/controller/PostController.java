package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostUserShortResponse;
import com.example.semestrovkacourse2sem2oris.service.PostRateService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostRateService postRateService;

    @GetMapping("/profile/{link}")
    public String getProfile(@PathVariable("link") String link,
                             Model model) {
        PostUserShortResponse postShortResponse = postService.getUserShortByLink(link);
        Integer userRating = postRateService.getCurrentUserRate(link);
        model.addAttribute("postUser", postShortResponse);
        model.addAttribute("rating", userRating);
        return "normal/post-profile";
    }
}
