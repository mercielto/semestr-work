package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;


@Entity
@Table(name = "account")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;
    private String username;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String login;
    private String password;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'USER'")
    private Role role;

    @ColumnDefault("true")
    private boolean active;

    private String link;

    @OneToMany(mappedBy = "creator")
    private List<PostEntity> posts;

    public boolean isActive() {
        return active;
    }

    public boolean isBanned() {
        return !active;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}
