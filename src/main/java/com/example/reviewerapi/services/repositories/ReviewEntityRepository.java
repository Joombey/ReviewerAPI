package com.example.reviewerapi.services.repositories;

import com.example.reviewerapi.models.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByReportIsNotNull();

    List<ReviewEntity> findByAuthor_LoginNot(String login);
}