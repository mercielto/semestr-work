package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue
    private Long postId;

    @Column(columnDefinition = "varchar(255) default 'default.png'", nullable = false)
    @Builder.Default
    private String imagePath = "default.png";
    private Integer readCount;      // количество прочитанных

    @Builder.Default
    private String title = "Post";

    @Column(columnDefinition = "boolean default false")
    private boolean published = false;      // видимость для других пользователей, если public

    @Column(unique = true, nullable = false)
    private String webLink;
    private String universe;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_editor",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    @Builder.Default
    private List<UserEntity> editors = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostRateEntity> ratedPosts;

    // TODO: проверить удаление всех зависимых объектов при удалении поста
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post")
    @Builder.Default
    private List<BranchEntity> branches = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity creator;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post")
    @Builder.Default
    private List<PostRateEntity> postRates = new ArrayList<>();

    @Formula("(SELECT COALESCE(avg(rate.rating), 0) FROM post_rate rate WHERE rate.post_id = post_id)")
    private Float averageRating;

    @Formula("(SELECT COALESCE(count(rate.rating), 0) FROM post_rate rate WHERE rate.post_id = post_id)")
    private int ratesCount;

    private String description;         // для генерации картинки через api, если получится
    private String creatorComment;      // в целом, скакой целью и тд (все что угодно)

    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_post_read",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    @Builder.Default
    private List<UserEntity> readUsers = new ArrayList<>();
}
