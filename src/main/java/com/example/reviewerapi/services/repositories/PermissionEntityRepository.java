package com.example.reviewerapi.services.repositories;

import com.example.reviewerapi.models.entities.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionEntityRepository extends JpaRepository<PermissionEntity, String> {
}
