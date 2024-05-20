package com.example.semestrovkacourse2sem2oris.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {

    private String login;
    private String email;
    private String password;
}
