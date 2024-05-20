package com.example.semestrovkacourse2sem2oris.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchCommentRequest {

    private String comment;
    private Date date;
}
