package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
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
    private final BranchService branchService;
    private final ChapterService chapterService;

    @GetMapping("/create")
    public RedirectView create() {
        PostResponse response = postService.create();
        return new RedirectView("create/settings/%s".formatted(response.getWebLink()));
    }

    @GetMapping("/create/settings/{link}")
    public String settings(@PathVariable("link") String link,
                           Model model) {
        PostShortResponse response = postService.getShortByLink(link);
        model.addAttribute("post", response);
        return "normal/post-create-settings";
    }

    @GetMapping("/create/chapters/{link}")
    public String chapters(@PathVariable("link") String link,
                            @RequestParam(value = "branch", required = false) String branchLink,
                            Model model) {
        PostResponse postResponse = postService.getByLink(link);
        if (branchLink == null) {
            branchLink = branchService.getMain(postResponse.getBranches()).getLink();
        }
        model.addAttribute("post", postResponse);
        model.addAttribute("branchLink", branchLink);
        return  "normal/post-create-chapters";
    }

    @GetMapping("/create/chapters/redir/{link}")
    public RedirectView chapters(@PathVariable("link") String link) {
        BranchResponse response = branchService.getByPostLink(link);
        return new RedirectView("/create/chapters/%s?branch=%s".formatted(link, response.getLink()));
    }

    @GetMapping("/create/chapter-add/{link}")
    public String chapterAdd(@PathVariable("link") String branchLink,
                             Model model) {
        PostShortResponse response = postService.getShortByBranchLink(branchLink);
        model.addAttribute("post", response);
        return "normal/post-create-chapter-add";
    }

    @GetMapping("/create/chapter-edit/{link}")
    public String chapterEdit(@PathVariable("link") String link,
                              Model model) {
        PostShortResponse response = postService.getShortByLink(link);
        model.addAttribute("post", response);
        return "normal/post-create-chapter-add-edit";
    }

    @PostMapping("/create/{link}")
    public void publishPost(@PathVariable("link") String link,
                            @ModelAttribute PostRequest request) {
        postService.publish(request, link);
    }

    @GetMapping("/{link}")
    public PostResponse getPost(@PathVariable("link") String link) {
        return postService.getByLink(link);
    }

    @GetMapping("/create/chapter/{chapterLink}")
    public String getChapter(@PathVariable("chapterLink") String chapterLink,
                             Model model) {
        ChapterResponse response = chapterService.getByLink(chapterLink);
        PostShortResponse postShortResponse = postService.getByChapterLink(chapterLink);
        model.addAttribute("chapter", response);
        model.addAttribute("post", postShortResponse);
        return "normal/post-create-chapter-add-edit";
    }
}
