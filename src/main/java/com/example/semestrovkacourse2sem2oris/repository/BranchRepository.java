package com.example.semestrovkacourse2sem2oris.repository;

import com.example.semestrovkacourse2sem2oris.model.BranchEntity;
import com.example.semestrovkacourse2sem2oris.model.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {

    Optional<BranchEntity> findByLink(String branchLink);

    @Transactional
    @Modifying
    @Query("update BranchEntity set description = :description, name = :name where link = :link")
    void updateByLink(@Param("link") String link,
                      @Param("description") String description,
                      @Param("name") String name);

    Page<BranchEntity> findAllByPostAndPublished(PostEntity post, Pageable pageable, boolean published);

    Page<BranchEntity> findAllByPostAndPublishedOrderByAverageRatingAsc(PostEntity post,
                                                                        Pageable pageable, boolean published);

    Page<BranchEntity> findAllByPostAndPublishedOrderByAverageRatingDesc(PostEntity post,
                                                                        Pageable pageable, boolean published);

//    @Transactional
//    @Query("SELECT b FROM BranchEntity b JOIN b.branchRates br where b.post = :post " +
//            "and b.published = :published GROUP BY b ORDER BY SIZE(br) DESC")
//    Page<BranchEntity> findAllOrderByBranchRatesSizeDesc(@Param("post") PostEntity post,
//                                                         Pageable pageable,
//                                                         @Param("published") boolean published);

    Page<BranchEntity> findAllByPostAndPublishedOrderByRatesCountDesc(PostEntity post,
                                                                       Pageable pageable, boolean published);

//    @Transactional
//    @Query("SELECT b FROM BranchEntity b JOIN b.branchRates br where b.post = :post " +
//            "and b.published = :published GROUP BY b ORDER BY SIZE(br) asc")
//    Page<BranchEntity> findAllOrderByBranchRatesSizeAsc(@Param("post") PostEntity post,
//                                                         Pageable pageable,
//                                                         @Param("published") boolean published);

    Page<BranchEntity> findAllByPostAndPublishedOrderByRatesCountAsc(PostEntity post,
                                                                      Pageable pageable, boolean published);
}
