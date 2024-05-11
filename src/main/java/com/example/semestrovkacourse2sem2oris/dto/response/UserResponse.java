package com.example.semestrovkacourse2sem2oris.dto.response;

import com.example.semestrovkacourse2sem2oris.model.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String name;
    private String login;
    private String email;
    private Role role;
    private boolean active;
    private String link;
}
