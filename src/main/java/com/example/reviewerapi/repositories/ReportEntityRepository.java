package com.example.reviewerapi.repositories;

import com.example.reviewerapi.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReportEntityRepository extends JpaRepository<ReportEntity, Integer> {
    @Transactional
    @Modifying
    @Query("delete from ReportEntity r where r.review.id = :id")
    int deleteByReviewId(Integer id);

    @Query("select (count(r) > 0) from ReportEntity r where r.review.id = :id")
    boolean existsByReviewId(@Param("id") Integer id);

    @Query("select r from ReportEntity r where r.review.id = :id")
    ReportEntity findByReviewId(@Param("id") Integer id);
}