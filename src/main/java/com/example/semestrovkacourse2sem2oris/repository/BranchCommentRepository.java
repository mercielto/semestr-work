package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.model.BranchCommentEntity;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchCommentRepository extends JpaRepository<BranchCommentEntity, Long> {

    Page<BranchCommentEntity> findAllByBranch(BranchEntity branch, Pageable pageable);
}
