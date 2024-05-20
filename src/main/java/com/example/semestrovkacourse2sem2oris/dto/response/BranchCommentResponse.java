package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchCommentResponse {

    private Long branchId;
    private UserResponse user;
    private Date date;
    private String comment;
}
