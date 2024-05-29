package com.example.semestrovkacourse2sem2oris.controller;

import com.example.semestrovkacourse2sem2oris.annotation.NotRestExceptionAnnotation;
import com.example.semestrovkacourse2sem2oris.dto.request.PostRequest;
import com.example.semestrovkacourse2sem2oris.dto.response.*;
import com.example.semestrovkacourse2sem2oris.service.BranchRateService;
import com.example.semestrovkacourse2sem2oris.service.BranchService;
import com.example.semestrovkacourse2sem2oris.service.ChapterService;
import com.example.semestrovkacourse2sem2oris.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/post/create")
@NotRestExceptionAnnotation
public class PostCreateController {

    private final PostService postService;
    private final BranchService branchService;
    private final ChapterService chapterService;

    @GetMapping({"/", ""})
    public RedirectView create() {
        PostResponse response = postService.create();
        return new RedirectView("settings/%s".formatted(response.getWebLink()));
    }

    @GetMapping("/settings/{link}")
    public String settings(@PathVariable("link") String link,
                           Model model) {
        PostShortResponse response = postService.getShortByLink(link);
        model.addAttribute("post", response);
        return "normal/post-create-settings";
    }

    @PostMapping("/settings/{link}")
    public RedirectView publish(@PathVariable("link") String link,
                                @ModelAttribute PostRequest postRequest) {
        postService.publish(postRequest, link);
        return new RedirectView("/post/profile/" + link);
    }

    @GetMapping("/chapters/{link}")
    public String chapters(@PathVariable("link") String link,
                            @RequestParam(value = "branch", required = false) String branchLink,
                            Model model) {
        PostResponse postResponse = postService.getByLink(link);
        if (branchLink == null) {
            branchLink = branchService.getMain(postResponse.getBranches()).getLink();
        }
        Map<Integer, List<ChapterResponse>> content =
                postService.getOrderedContentByPostLinkAndBranchLink(link, branchLink);
        BranchShortResponse branch = branchService.getShortByLink(branchLink);

        ChapterResponse lastChapter = chapterService.getLastChapterByBranchLink(branchLink);
        ChapterResponse firstChapter = chapterService.getFirstChapterByBranchLink(branchLink);

        model.addAttribute("lastNumber", lastChapter.getNumber());
        model.addAttribute("minNumber", firstChapter.getNumber());
        model.addAttribute("post", postResponse);
        model.addAttribute("chapters", content);
        model.addAttribute("branch", branch);
        return  "normal/post-create-chapters";
    }

    @GetMapping("/chapters/redir/{link}")
    public RedirectView chapters(@PathVariable("link") String link) {
        BranchResponse response = branchService.getMainBranchByPostLink(link);
        return new RedirectView("/create/chapters/%s?branch=%s".formatted(link, response.getLink()));
    }

    @GetMapping("/chapter-add/{link}")
    public String chapterAdd(@PathVariable("link") String branchLink,
                             Model model) {
        PostShortResponse response = postService.getShortByBranchLink(branchLink);
        ChapterResponse lastChapter = chapterService.getLastChapterByBranchLink(branchLink);
        ChapterResponse firstChapter = chapterService.getFirstChapterByBranchLink(branchLink);
        model.addAttribute("post", response);
        model.addAttribute("lastNumber", lastChapter.getNumber());
        model.addAttribute("minNumber", firstChapter.getNumber());
        return "normal/post-create-chapter-add";
    }

    @GetMapping("/chapter-edit/{link}")
    public String chapterEdit(@PathVariable("link") String link,
                              Model model) {
        PostShortResponse response = postService.getShortByLink(link);
        model.addAttribute("post", response);
        return "normal/post-create-chapter-add-edit";
    }

    @GetMapping("/chapter/{chapterLink}")
    public String getChapter(@PathVariable("chapterLink") String chapterLink,
                             Model model) {
        ChapterResponse response = chapterService.getByLink(chapterLink);
        PostShortResponse postShortResponse = postService.getByChapterLink(chapterLink);
        ChapterResponse lastChapter = chapterService.getLastChapterByBranchLink(response.getBranchLink());
        ChapterResponse firstChapter = chapterService.getFirstChapterByBranchLink(response.getBranchLink());
        model.addAttribute("chapter", response);
        model.addAttribute("post", postShortResponse);
        model.addAttribute("lastNumber", lastChapter.getNumber());
        model.addAttribute("minNumber", firstChapter.getNumber());
        return "normal/post-create-chapter-add-edit";
    }
}
