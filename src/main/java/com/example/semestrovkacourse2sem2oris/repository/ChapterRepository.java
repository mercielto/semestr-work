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
    @Query("update ChapterEntity c set c.number = c.number + 1 where c.id != :id" +
            " and c.number >= :addedNumber and c.branch = :branch")
    void increaseNumber(@Param("id")Long id, @Param("addedNumber") Integer number,
                        @Param("branch") BranchEntity branch);

    @Modifying
    @Transactional
    @Query("update ChapterEntity c set c.number = c.number - 1 where c.id != :id " +
            "and c.number >= :addedNumber and c.branch = :branch")
    void decreaseNumber(@Param("id")Long id, @Param("addedNumber") Integer number,
                        @Param("branch") BranchEntity branch);

    Optional<ChapterEntity> findFirstByBranchOrderByNumberAsc(BranchEntity branch);

    Optional<ChapterEntity> findFirstByBranchOrderByNumberDesc(BranchEntity branch);

    @Transactional
    @Query("SELECT COUNT(ch) > 1 " +
            "FROM ChapterEntity ch " +
            "WHERE ch.number = (SELECT MIN(c.number) FROM ChapterEntity c WHERE c.branch = :branch) " +
            "AND ch.branch = :branch " +
            "AND ch.number = :number")
    boolean doesItHasBranchings(@Param("branch") BranchEntity branch, @Param("number") Integer number);
}
