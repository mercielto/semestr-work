package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.response.*;
import com.example.semestrovkacourse2sem2oris.service.BranchCommentService;
import com.example.semestrovkacourse2sem2oris.service.BranchRateService;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
@NotRestExceptionAnnotation
public class BranchController {

    private final BranchService branchService;
    private final PostService postService;
    private final BranchRateService branchRateService;
    private final BranchCommentService branchCommentService;

    @GetMapping("/profile/{link}")
    public String getProfile(@PathVariable("link") String branchLink,
                             Model model) {
        BranchShortResponse response = branchService.getShortByLink(branchLink);
        Integer rate = branchRateService.getCurrentUserRate(branchLink);
        model.addAttribute("branch", response);
        model.addAttribute("rating", rate);
        return "normal/branch-profile";
    }

    @GetMapping("/comments/{link}")
    public String getComments(@PathVariable("link") String link,
                              Model model) {
        List<BranchCommentResponse> comments =
                branchCommentService.getByBranchLinkWithPagination(link, 0, 5);
        model.addAttribute("comments", comments);
        return "normal/branch-comments";
    }


    @GetMapping("/create/profile/{link}")
    public String getBranchProfile(@PathVariable("link") String link,
                                   Model model) {
        BranchShortResponse response = branchService.getShortByLink(link);
        Integer rate = branchRateService.getCurrentUserRate(link);
        model.addAttribute("branch", response);
        model.addAttribute("rating", rate);
        return "normal/post-create-branch-profile";
    }

    @GetMapping("/create/read/{link}")
    public String getBranchContent(@PathVariable("link") String link,
                                   Model model) {
        BranchResponse branchResponse = branchService.getByLink(link);
        PostShortResponse postShortResponse = postService.getShortByBranchLink(link);
        model.addAttribute("branch", branchResponse);
        model.addAttribute("post", postShortResponse);
        return "normal/branch-create-read";
    }

    @GetMapping("/create/profile")
    public RedirectView createBranch(@RequestParam("branchLink") String link,
                                     @RequestParam("number") Integer number) {
        BranchResponse response = branchService.createByBranchLink(link, number);
        return new RedirectView("/branch/create/profile/%s".formatted(response.getLink()));
    }

    @GetMapping("/read/{link}")
    public RedirectView read(@PathVariable("link") String link) {
        PostShortResponse response = postService.getShortByBranchLink(link);
        return new RedirectView("/post/read/%s?branch=%s".formatted(response.getWebLink(), link));
    }
}
