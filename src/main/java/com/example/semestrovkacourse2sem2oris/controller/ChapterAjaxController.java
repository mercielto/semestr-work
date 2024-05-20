package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRateRequest;
import com.example.semestrovkacourse2sem2oris.dto.request.ChapterRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapter/ajax")
public class ChapterAjaxController {

    private final ChapterService chapterService;

    @PutMapping("/{link}")
    private String put(@RequestBody ChapterRequest chapterRequest,
                     @PathVariable("link") String chapterLink) {
        return chapterService.put(chapterLink, chapterRequest);
    }

    @DeleteMapping("/{link}")
    public void deleteChapter(@PathVariable("link") String postLink) {
        chapterService.deleteChapter(postLink);
    }
}
