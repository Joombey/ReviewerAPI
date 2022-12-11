package com.example.reviewerapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class PermissionEntity {
    @Id
    private String role;

    private Boolean review_maker_access;
    private Boolean profile_access;
    private Boolean review_block_access;
    private Boolean role_changer_access;

    @JsonManagedReference
    @OneToMany(mappedBy = "role")
    private List<UserEntity> userList;

    public PermissionEntity() {
    }

    public PermissionEntity(String role,
                            Boolean review_maker_access,
                            Boolean profile_access,
                            Boolean review_block_access,
                            Boolean role_changer_access) {
        this.role = role;
        this.review_maker_access = review_maker_access;
        this.profile_access = profile_access;
        this.review_block_access = review_block_access;
        this.role_changer_access = role_changer_access;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getReview_maker_access() {
        return review_maker_access;
    }

    public void setReview_maker_access(Boolean review_maker_access) {
        this.review_maker_access = review_maker_access;
    }

    public Boolean getProfile_access() {
        return profile_access;
    }

    public void setProfile_access(Boolean profile_access) {
        this.profile_access = profile_access;
    }

    public Boolean getReview_block_access() {
        return review_block_access;
    }

    public void setReview_block_access(Boolean review_block_access) {
        this.review_block_access = review_block_access;
    }

    public Boolean getRole_changer_access() {
        return role_changer_access;
    }

    public void setRole_changer_access(Boolean role_changer_access) {
        this.role_changer_access = role_changer_access;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }
}
