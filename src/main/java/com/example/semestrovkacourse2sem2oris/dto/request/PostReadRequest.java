package com.example.semestrovkacourse2sem2oris.dto.request;

import com.example.semestrovkacourse2sem2oris.model.PostReadStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostReadRequest {

    private PostReadStatus status;
}
