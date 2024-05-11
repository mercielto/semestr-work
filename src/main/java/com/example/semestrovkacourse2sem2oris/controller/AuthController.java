package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.UserRequest;
import com.example.semestrovkacourse2sem2oris.model.Role;
import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/sign-up")
    public RedirectView signUp(@ModelAttribute UserRequest request) {
        System.out.println("CREATION STARTED");
        if (!userService.check(request)) {
            return new RedirectView("/sign-up");
        }

        userService.create(request);
        return new RedirectView("/sign-in");
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "signIn";
    }

    @PostMapping("/sign-in")
    public RedirectView signIn(@ModelAttribute UserRequest request) {
        System.out.println("post method");
        return new RedirectView("profile");
    }

    @GetMapping("/get-admin")
    public RedirectView getAdmin() {
        userService.setRole(Role.ADMIN);
        System.out.println("ADMIN GAINED");
        return new RedirectView("/");
    }
}
