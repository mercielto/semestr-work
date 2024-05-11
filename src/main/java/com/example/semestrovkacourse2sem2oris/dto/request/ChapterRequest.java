package com.example.semestrovkacourse2sem2oris.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterRequest {

    private String title;
    private String text;
    private Integer number;
}
