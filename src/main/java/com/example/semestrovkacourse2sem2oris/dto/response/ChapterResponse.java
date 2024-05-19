package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterResponse {

    private Long id;
    private String title;
    private String text;
    private Integer number;
    private String branchLink;
    private String link;
}
