package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "branch_comment")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchCommentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String comment;
    private Date date;
}
