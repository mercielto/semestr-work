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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_generator")
    @SequenceGenerator(name = "common_generator", sequenceName = "common_sequence", initialValue = 1000)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Integer rating;
}
