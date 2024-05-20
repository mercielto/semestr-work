package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
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

    @Builder.Default
    @Column(columnDefinition = "varchar(100) default 'User'")
    private String username = "User";

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BranchCommentEntity> branchComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RatedPostEntity> ratedPosts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BranchRateEntity> ratedBranches = new ArrayList<>();

    @Column(unique = true)
    private String login;
    private String password;

    @Builder.Default
    private String imageName = "default.png";

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'USER'")
    private Role role;

    @ColumnDefault("true")
    private boolean active;

    private String link;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
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
