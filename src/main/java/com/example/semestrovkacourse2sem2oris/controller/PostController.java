package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final ChapterService chapterService;

    @GetMapping("/create")
    public RedirectView create() {
        PostResponse response = postService.create();
        return new RedirectView("create/%s?part=settings".formatted(response.getWebLink()));
    }

//    @GetMapping("/create/{link}")
//    public String createPage(Model model, @PathVariable String link) {
//        PostResponse response = postService.getByLink(link);
//        model.addAttribute("post", response);
//        return "post-create-settings";
//    }

    @GetMapping("/create/{link}")
    public String createPage(Model model,
                             @PathVariable String link,
                             @RequestParam("part") String part) {
        PostResponse response = postService.getByLink(link);
        model.addAttribute("post", response);

        return switch (part) {
            case "chapters" -> "normal/post-create-chapters";
            case "chapter-add" -> "normal/post-create-chapter-add";
            default -> "normal/post-create-settings";
        };
    }

    @PostMapping("/create/chapter-add/{link}")
    public void addChapter(@RequestBody ChapterRequest request,
                             @PathVariable String link) {
        chapterService.add(request, link);
    }

    @PostMapping("/create/{link}")
    public void create(@ModelAttribute PostRequest request) {
        postService.create(request);
    }

    @GetMapping("/{link}")
    public PostResponse getPost(@PathVariable("link") String link) {
        return postService.getByLink(link);
    }
}
