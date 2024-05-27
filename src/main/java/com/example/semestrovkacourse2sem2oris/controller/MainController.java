package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
@NotRestExceptionAnnotation
public class MainController {

    private final UserService userService;

    @GetMapping({"", "/"})
    public String getMain(Model model) {
        Optional<UserResponse> userResponse = userService.getCurrentUserResponse();
        model.addAttribute("navUser", userResponse);
        return "normal/main";
    }
}
