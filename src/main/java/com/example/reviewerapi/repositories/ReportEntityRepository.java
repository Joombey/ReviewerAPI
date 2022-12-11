package com.example.reviewerapi.repositories;

import com.example.reviewerapi.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportEntityRepository extends JpaRepository<ReportEntity, Integer> {
    long deleteByReview_Id(Integer id);
}