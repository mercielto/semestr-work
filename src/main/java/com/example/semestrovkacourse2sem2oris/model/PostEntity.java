package com.example.semestrovkacourse2sem2oris.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

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
    private String imagePath = "default.png";
    private Integer readCount;      // количество прочитанных
    private String title;

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
    private List<UserEntity> editors = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<BranchEntity> branches = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity creator;

    @OneToMany(mappedBy = "post")
    private List<ChapterEntity> chapters = new ArrayList<>();

    private String description;         // для генерации картинки через api, если получится
    private String creatorComment;      // в целом, скакой целью и тд (все что угодно)
//    private String sourcePath;          // ссылка на диске

    @Enumerated(value = EnumType.STRING)
    private PostStatus status;
}
