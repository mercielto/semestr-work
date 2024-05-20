package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/ajax")
@RequiredArgsConstructor
public class UserAjaxController {

    private final UserService userService;

    @PutMapping("/follow/{link}")
    public void follow(@PathVariable("link") String link) {
        userService.changeFollowByLink(link);
    }

    @GetMapping("/subscribed/{link}")
    public boolean isCurrentSubscribed(@PathVariable("link") String link) {
        return userService.isCurrentSubscribed(link);
    }

    @PatchMapping("/username/{link}/{username}")
    public void changeUsername(@PathVariable("link") String link,
                               @PathVariable("username") String username) {
        userService.updateUsernameByLink(link, username);
    }

    @PatchMapping("/bio/{link}/{bio}")
    public void changeBio(@PathVariable("link") String link,
                          @PathVariable("bio") String bio) {
        userService.updateBioByLink(link, bio);
    }

    @PatchMapping("/image/{link}")
    public void changeImage(@RequestParam("file") MultipartFile file,
                            @PathVariable("link") String link) {
        userService.updateImage(file, link);
    }
}
