package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "branch_rate")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchRateEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Integer rating;
}
