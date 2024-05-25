package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.ChapterEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<ChapterEntity, Long> {

    Optional<ChapterEntity> findByLink(String link);

    @Modifying
    @Transactional
    @Query("update ChapterEntity c set c.number = c.number + 1 where c.id != :id and c.number >= :addedNumber")
    void increaseNumber(@Param("id")Long id, @Param("addedNumber") Integer number);

    @Modifying
    @Transactional
    @Query("update ChapterEntity c set c.number = c.number - 1 where c.id != :id and c.number >= :addedNumber")
    void decreaseNumber(@Param("id")Long id, @Param("addedNumber") Integer number);

    Optional<ChapterEntity> findFirstByBranchOrderByNumberAsc(BranchEntity branch);
}
