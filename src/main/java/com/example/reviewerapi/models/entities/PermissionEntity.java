package com.example.reviewerapi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class PermissionEntity {
    @Id
    private String role;

    @Column(name = "review_maker_access")
    private Boolean reviewMakerAccess;
    @Column(name = "profile_access")
    private Boolean profileAccess;
    @Column(name = "review_block_access")
    private Boolean reviewBlockAccess;
    @Column(name = "role_changer_access")
    private Boolean roleChangerAccess;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "role")
    private List<UserEntity> userList;

    public PermissionEntity() {
    }

    public PermissionEntity(String role,
                            Boolean reviewMakerAccess,
                            Boolean profileAccess,
                            Boolean reviewBlockAccess,
                            Boolean roleChangerAccess) {
        this.role = role;
        this.reviewMakerAccess = reviewMakerAccess;
        this.profileAccess = profileAccess;
        this.reviewBlockAccess = reviewBlockAccess;
        this.roleChangerAccess = roleChangerAccess;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getReviewMakerAccess() {
        return reviewMakerAccess;
    }

    public void setReviewMakerAccess(Boolean reviewMakerAccess) {
        this.reviewMakerAccess = reviewMakerAccess;
    }

    public Boolean getProfileAccess() {
        return profileAccess;
    }

    public void setProfileAccess(Boolean profileAccess) {
        this.profileAccess = profileAccess;
    }

    public Boolean getReviewBlockAccess() {
        return reviewBlockAccess;
    }

    public void setReviewBlockAccess(Boolean reviewBlockAccess) {
        this.reviewBlockAccess = reviewBlockAccess;
    }

    public Boolean getRoleChangerAccess() {
        return roleChangerAccess;
    }

    public void setRoleChangerAccess(Boolean roleChangerAccess) {
        this.roleChangerAccess = roleChangerAccess;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }
}
