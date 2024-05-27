package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.request.UserRegistrationRequest;
import com.example.semestrovkacourse2sem2oris.model.Role;
import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/authentication")
@RequiredArgsConstructor
@NotRestExceptionAnnotation
public class AuthController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        return "normal/signUp";
    }

    @PostMapping("/sign-up")
    public RedirectView signUp(@ModelAttribute UserRegistrationRequest request) {
        if (!userService.check(request)) {
            return new RedirectView("/sign-up");
        }

        userService.create(request);
        return new RedirectView("/sign-in");
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        return "normal/signIn";
    }

    @PostMapping("/sign-in")
    public RedirectView signIn(@ModelAttribute UserRegistrationRequest request) {
        System.out.println("post method");
        return new RedirectView("profile");
    }
}
