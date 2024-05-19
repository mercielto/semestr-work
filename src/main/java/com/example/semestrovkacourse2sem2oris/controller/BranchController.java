package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.response.BranchShortResponse;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/{link}")
    public String get(@PathVariable("link") String link,
                      Model model) {
        BranchShortResponse response = branchService.getShortByLink(link);
        model.addAttribute("branch", response);
        return "normal/branch-profile";
    }
}
