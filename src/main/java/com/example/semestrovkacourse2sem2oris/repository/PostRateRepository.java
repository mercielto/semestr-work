package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.model.BranchRateEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import com.example.semestrovkacourse2sem2oris.model.PostRateEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRateRepository extends JpaRepository<PostRateEntity, Long> {

    Optional<PostRateEntity> findByUserAndPost(UserEntity user, PostEntity post);
}
