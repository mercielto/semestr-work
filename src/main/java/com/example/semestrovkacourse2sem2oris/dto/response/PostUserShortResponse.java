package com.example.semestrovkacourse2sem2oris.dto.response;

import com.example.semestrovkacourse2sem2oris.model.PostReadStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUserShortResponse {

    private PostShortResponse response;
    private PostReadStatus status;
}
