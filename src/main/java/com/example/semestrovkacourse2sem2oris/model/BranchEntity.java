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

    public float getAverageRating() {
        if (branchRates == null || branchRates.isEmpty()) {
            return 0;
        }
        float sum = 0;
        for (BranchRateEntity rate : branchRates) {
            sum += rate.getRating();
        }
        return sum / branchRates.size();
    }
}


