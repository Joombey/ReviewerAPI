package com.example.reviewerapi.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "role")
    private PermissionEntity role;

    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList;

    public UserEntity(String login, String password, String city, String avatar, PermissionEntity role) {
        this.login = login;
        this.password = password;
        this.city = city;
        this.avatar = avatar;
        this.role = role;
    }

    public UserEntity() {}

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
