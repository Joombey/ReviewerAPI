package com.example.reviewerapi.repositories;

import com.example.reviewerapi.entities.PermissionEntity;
import com.example.reviewerapi.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByLoginAndPassword(String login, String password);
    boolean existsByLoginAndRole_Role(String login, String role);

    @Query("""
            select (count(u) > 0) from UserEntity u
            where upper(u.login) = upper(:login) and upper(u.role.role) = upper(:role)
            """)
    boolean existByLoginAndRole(@Param("login") String login, @Param("role") String role);
}