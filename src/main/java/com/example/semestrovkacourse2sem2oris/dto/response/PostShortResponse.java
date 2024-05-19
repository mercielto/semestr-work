package com.example.semestrovkacourse2sem2oris.dto.response;

import com.example.semestrovkacourse2sem2oris.model.PostStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostShortResponse {

    private String imagePath;
    private String title;
    private String webLink;
    private UserResponse creator;
    private String universe;
    private String description;
    private PostStatus status;
    private String creatorComment;
}
