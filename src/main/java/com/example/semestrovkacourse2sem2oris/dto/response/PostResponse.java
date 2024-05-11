package com.example.semestrovkacourse2sem2oris.dto.response;

import com.example.semestrovkacourse2sem2oris.model.PostStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private String imagePath;
    private Integer readCount;
    private String title;
    private String webLink;

    private List<UserResponse> editors;
    private List<BranchResponse> branches;
    private List<ChapterResponse> chapters;

    private UserResponse creator;
    private String universe;

    private String description;
    private String creatorComment;
//    private String sourcePath;
    private PostStatus status;
}
