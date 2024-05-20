package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserExtendedResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile/{link}")
    public String getProfile(@PathVariable("link") String link,
                             Model model) {
        UserExtendedResponse userResponse = userService.getByLink(link);
        model.addAttribute("user", userResponse);

        if (userService.isCurrentUserIsOwner(link)) {
            return "normal/user-profile-owner";
        }
        return "normal/user-profile";
    }
}
