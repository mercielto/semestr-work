package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String name;
    private String link;
    private boolean main;

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity creator;

    @OrderBy("number ASC")
    @OneToMany(fetch = FetchType.LAZY)
    private List<ChapterEntity> chapters;       // главы в ветке
}


