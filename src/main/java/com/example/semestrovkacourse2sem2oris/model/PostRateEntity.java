package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_rate")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_generator")
    @SequenceGenerator(name = "common_generator", sequenceName = "common_sequence", initialValue = 1000)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    private int rating;
}
