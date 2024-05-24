package com.example.semestrovkacourse2sem2oris.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "post_comment")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String comment;
    private Date date;
}
