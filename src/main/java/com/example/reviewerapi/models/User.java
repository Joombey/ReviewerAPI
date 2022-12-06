package com.example.reviewerapi.models;

import com.example.reviewerapi.models.embedable.UserId;
import com.example.reviewerapi.requests.UserRequest;

public class User {
    private UserId id;

    private String city;
    private String avatar;
    private String role;

    public User(UserId id, String city, String avatar, String role) {
        this.id = id;
        this.city = city;
        this.avatar = avatar;
        this.role = role;
    }

    public User(UserRequest user) {
        this.id = user.getId();
        this.city = user.getCity();
        this.avatar = user.getAvatar();
        this.role = Permission.USER.getRole();
    }

    public UserId getId() {
        return id;
    }

    public void setId(UserId id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
