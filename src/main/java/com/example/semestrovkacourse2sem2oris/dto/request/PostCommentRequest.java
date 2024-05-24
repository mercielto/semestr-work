package com.example.semestrovkacourse2sem2oris.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentRequest {

    private String comment;
    private Date date;
}
