package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

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

    @Builder.Default
    private boolean published = false;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BranchRateEntity> branchRates = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "postId")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity creator;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BranchCommentEntity> comments;

    @OrderBy("number ASC")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "branch")
    @Builder.Default
    private List<ChapterEntity> chapters = new ArrayList<>();       // главы в ветке

    @Formula("(SELECT COALESCE(avg(rate.rating), 0) FROM branch_rate rate WHERE rate.branch_id = branch_id)")
    private Float averageRating;

    @Formula("(SELECT COALESCE(count(rate.rating), 0) FROM branch_rate rate WHERE rate.branch_id = branch_id)")
    private int ratesCount;
}


