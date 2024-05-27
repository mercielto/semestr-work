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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_generator")
    @SequenceGenerator(name = "common_generator", sequenceName = "common_sequence", initialValue = 1000)
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
