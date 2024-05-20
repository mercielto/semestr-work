package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.BranchRateEntity;
import com.example.semestrovkacourse2sem2oris.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRateRepository extends JpaRepository<BranchRateEntity, Long> {

    Optional<BranchRateEntity> findByUserAndBranch(UserEntity user, BranchEntity branch);
}
