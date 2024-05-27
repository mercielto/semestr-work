package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.RestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.request.BranchRateRequest;
import com.example.semestrovkacourse2sem2oris.service.BranchRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch/ajax/rate")
@RequiredArgsConstructor
@RestExceptionAnnotation
public class BranchRateAjaxController {

    private final BranchRateService branchRateService;

    @PutMapping("/{link}")
    public void rate(@PathVariable("link") String link,
                     @RequestBody BranchRateRequest request) {
        branchRateService.rate(link, request);
    }
}
