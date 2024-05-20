package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.dto.request.BranchRequest;
import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
    Optional<BranchEntity> findByLink(String branchLink);

    @Transactional
    @Modifying
    @Query("update BranchEntity set description = :description, name = :name where link = :link")
    void updateByLink(@Param("link") String link,
                      @Param("description") String description,
                      @Param("name") String name);
}
