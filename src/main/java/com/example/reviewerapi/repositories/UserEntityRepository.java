package com.example.reviewerapi.repositories;

import com.example.reviewerapi.entities.PermissionEntity;
import com.example.reviewerapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    boolean existsByLoginAndPassword(String login, String password);
    UserEntity findByLoginAndPassword(String login, String password);

    boolean existsByLoginAndRole_Role(String login, String role);


}