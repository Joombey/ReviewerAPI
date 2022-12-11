package com.example.reviewerapi.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String login;
    @Column(nullable = false)
    private String password;
    private String city;
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "role")
    private PermissionEntity role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public PermissionEntity getRole() {
        return role;
    }

    public void setRole(PermissionEntity role) {
        this.role = role;
    }

    public List<ReviewEntity> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<ReviewEntity> reviewList) {
        this.reviewList = reviewList;
    }
}
