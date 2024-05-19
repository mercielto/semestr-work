package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chapter")
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/{link}")
    private RedirectView create(@ModelAttribute ChapterRequest chapterRequest,
                                @PathVariable("link") String branchLink) {
        chapterService.add(chapterRequest, branchLink);
        return new RedirectView("/post/create/%s?part=chapters".formatted(branchLink));
    }
}
