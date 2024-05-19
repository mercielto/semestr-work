package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branch")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    @GeneratedValue
    private Long branchId;
    // TODO: сделать проверку на доступ пользователям, не являющимся создателями
    private String name;
    private String link;
    private boolean main;
    private String description;

    @Column(columnDefinition = "float default 0")
    @Builder.Default
    private float rating = 0;

    @Column(columnDefinition = "bigint default 0")
    @Builder.Default
    private Long readCount = 0L;

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity creator;

    @OrderBy("number ASC")
    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChapterEntity> chapters = new ArrayList<>();       // главы в ветке
}


