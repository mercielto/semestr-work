package com.example.semestrovkacourse2sem2oris.dto.request;

import com.example.semestrovkacourse2sem2oris.model.PostStatus;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {

    private String title;
    private String universeName;

    private List<String> editorsLogins;

    private String description;
    private String creatorComment;
    private String status;
}
