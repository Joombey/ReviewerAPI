package com.example.reviewerapi.repositories;

import com.example.reviewerapi.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionEntityRepository extends JpaRepository<PermissionEntity, String> {
    boolean existsByRole(String role);
}
