package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchUserShortResponse {

    private BranchShortResponse response;
    private int rating;
}
