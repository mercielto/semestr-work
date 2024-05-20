package com.example.semestrovkacourse2sem2oris.dto.request;

import com.example.semestrovkacourse2sem2oris.model.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String email;
    private String bio;
}
