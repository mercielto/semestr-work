package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.PostShortResponse;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chapter")
@NotRestExceptionAnnotation
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/{link}")
    private RedirectView create(@ModelAttribute ChapterRequest chapterRequest,
                                @PathVariable("link") String branchLink) {
        chapterService.add(chapterRequest, branchLink);
        return new RedirectView("/branch/create/read/%s".formatted(branchLink));
    }

    @GetMapping("/{link}")
    private String getPage(@PathVariable("link") String link,
                           Model model) {
        ChapterResponse chapter = chapterService.getByLink(link);
        model.addAttribute("chapter", chapter);
        return "normal/chapter";
    }
}
