package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String username;
    private String login;
    private String email;
    private String link;
    private String imageName;
    private String bio;
    private int followersCount;
    private int followingsCount;
}
