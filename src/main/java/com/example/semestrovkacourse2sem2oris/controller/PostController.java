package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.response.*;
import com.example.semestrovkacourse2sem2oris.model.SortType;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import com.example.semestrovkacourse2sem2oris.service.PostCommentService;
import com.example.semestrovkacourse2sem2oris.service.PostRateService;
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
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostRateService postRateService;
    private final PostCommentService postCommentService;
    private final BranchService branchService;

    @GetMapping("/profile/{link}")
    public String getProfile(@PathVariable("link") String link,
                             Model model) {
        PostUserShortResponse postShortResponse = postService.getUserShortByLink(link);
        Integer userRating = postRateService.getCurrentUserRate(link);
        model.addAttribute("postUser", postShortResponse);
        model.addAttribute("rating", userRating);
        return "normal/post-profile";
    }

    @GetMapping("/comments/{link}")
    public String getComments(@PathVariable("link") String link,
                              Model model) {
        List<PostCommentResponse> comments =
                postCommentService.getByPostLinkWithPagination(link, 0, 5);
        model.addAttribute("comments", comments);
        return "normal/post-comments";
    }

    @GetMapping("/vote/{link}")
    public String getVote(@PathVariable("link") String link,
                          Model model) {
        List<BranchUserShortResponse> branches =
                branchService.getPublishedUserShortByPostLinkWithPagination(
                        link, 0, 5, SortType.AVERAGE_RATING_DESC);
        model.addAttribute("branchesUser", branches);
        return "normal/post-vote";
    }

    @GetMapping("/read/{link}")
    public String getContent(@PathVariable("link") String postLink,
                             @RequestParam(value = "branch") String branchLink,
                             Model model) {

        Map<Integer, List<ChapterResponse>> content =
                postService.getOrderedContentByPostLinkAndBranchLink(postLink, branchLink);
        model.addAttribute("chapters", content);
        return "normal/post-chapters";
    }

    @GetMapping("/read/main/{link}")
    public RedirectView redirectToMainBranch(@PathVariable("link") String postLink) {
        BranchResponse branch = branchService.getMainBranchByPostLink(postLink);
        return new RedirectView("/post/read/%s?branch=%s".formatted(postLink, branch.getLink()));
    }
}
