package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.response.BranchCommentResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.service.BranchCommentService;
import com.example.semestrovkacourse2sem2oris.service.BranchRateService;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;
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
}
