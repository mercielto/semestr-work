package com.example.semestrovkacourse2sem2oris.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String login;
    private String email;
    private String password;
}
