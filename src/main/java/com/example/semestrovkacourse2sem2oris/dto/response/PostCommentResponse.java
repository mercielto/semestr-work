package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentResponse {

    private Long postId;
    private UserResponse user;
    private Date date;
    private String comment;
}
