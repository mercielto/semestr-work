package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchCommentRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.BranchRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchCommentResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.BranchUserShortResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostContentResponse;
import com.example.semestrovkacourse2sem2oris.model.SortType;
import com.example.semestrovkacourse2sem2oris.service.BranchCommentService;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch/ajax")
@RequiredArgsConstructor
public class BranchAjaxController {

    private final BranchCommentService branchCommentService;
    private final BranchService branchService;

    @GetMapping("/comments/{link}/{from}/{count}")
    public List<BranchCommentResponse> getComments(@PathVariable("link") String link,
                                                   @PathVariable("from") Integer from,
                                                   @PathVariable("count") Integer count) {
        return branchCommentService.getByBranchLinkWithPagination(link, from, count);
    }

    @PostMapping("/comment/{link}")
    public BranchCommentResponse postComment(@PathVariable("link") String link,
                                             @RequestBody BranchCommentRequest request) {
        return branchCommentService.createByBranchLink(link, request);
    }

    @PutMapping("/profile/{link}")
    public void putChanges(@RequestBody BranchRequest request,
                           @PathVariable("link") String link) {
        branchService.update(link, request);
    }

    @GetMapping("/{link}")
    public List<BranchUserShortResponse> getBranches(@PathVariable("link") String link,
                                                     @RequestParam("from") Integer from,
                                                     @RequestParam("count") Integer count,
                                                     @RequestParam("sort") String sort) {
        return branchService.getPublishedUserShortByPostLinkWithPagination(link, from, count, SortType.valueOf(sort));
    }

    @GetMapping("/content/{link}")
    public PostContentResponse getContentOfBranch(@PathVariable("link") String branchLink) {
        return PostContentResponse.builder()
                .chapters(branchService.getOrderedContentByBranchLink(branchLink))
                .branch(branchService.getShortByLink(branchLink))
                .build();
    }
}
