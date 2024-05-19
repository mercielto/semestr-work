package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponse {

    private String name;
    private String link;
    private String postLink;
    private UserResponse creator;
    private List<ChapterResponse> chapters;
    private ChapterResponse mainChapter;
    private boolean main;
}
