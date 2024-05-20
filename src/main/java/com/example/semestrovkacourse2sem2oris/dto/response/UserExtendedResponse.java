package com.example.semestrovkacourse2sem2oris.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExtendedResponse {

    private String username;
    private String login;
    private String email;
    private String link;
    private String imageName;
    private String bio;
    private int followersCount;
    private int followingsCount;
    private List<PostShortResponse> posts;
}
