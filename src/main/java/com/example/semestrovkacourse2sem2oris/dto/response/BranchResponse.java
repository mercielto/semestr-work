package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchResponse {

    private String name;
    private String sourcePath;
    private PostResponse post;
    private UserResponse creator;
}
