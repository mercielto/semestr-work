package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchShortResponse {

    private String name;
    private String link;
    private String postLink;
    private String postName;
    private UserResponse creator;
    private boolean main;
    private String description;
    private float rating;
}
