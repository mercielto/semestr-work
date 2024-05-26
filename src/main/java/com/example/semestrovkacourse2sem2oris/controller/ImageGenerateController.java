package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image/generate")
public class ImageGenerateController {

    private final PostService postService;

    @PostMapping("/{link}")
    public String generateImage(@PathVariable("link") String postLink,
                                @RequestBody(required = false) String text) {
        return postService.generateImage(postLink, text);
    }
}
