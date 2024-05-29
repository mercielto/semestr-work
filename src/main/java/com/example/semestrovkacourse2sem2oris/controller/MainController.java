package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
@NotRestExceptionAnnotation
public class MainController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping({"", "/"})
    public String getMain(Model model) {
        Optional<UserResponse> userResponse = userService.getCurrentUserResponse();
        model.addAttribute("navUser", userResponse);
        return "normal/main";
    }

    @GetMapping("/search")
    public String getSearch(Model model) {
        List<PostShortResponse> posts = postService.getWithPagination(0, 9);
        model.addAttribute("posts", posts);
        return "normal/search";
    }

    @GetMapping("/publications")
    public String getPublications(Model model) {
        List<PostShortResponse> posts = postService.getByUserWithPagination(0, 9);
        model.addAttribute("posts", posts);
        return "normal/search";
    }
}
