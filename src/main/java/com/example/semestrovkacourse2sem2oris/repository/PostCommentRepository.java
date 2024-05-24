package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.model.PostCommentEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostCommentEntity, Long> {

    List<PostCommentEntity> findAllByPost(PostEntity post, Pageable pageable);
}
