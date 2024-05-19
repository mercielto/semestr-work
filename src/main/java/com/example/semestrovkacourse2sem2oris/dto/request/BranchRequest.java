package com.example.semestrovkacourse2sem2oris.dto.request;

import com.example.semestrovkacourse2sem2oris.dto.response.ChapterResponse;
import com.example.semestrovkacourse2sem2oris.dto.response.UserResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchRequest {

    private String name;
    private String description;
}
