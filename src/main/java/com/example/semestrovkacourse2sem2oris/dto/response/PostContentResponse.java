package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostContentResponse {

    private Map<Integer, List<ChapterResponse>> chapters;
    private BranchShortResponse branch;
}
