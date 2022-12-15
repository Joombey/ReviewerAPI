package com.example.reviewerapi.responses;

import com.example.reviewerapi.entities.UserEntity;
import com.example.reviewerapi.models.User;
import com.example.reviewerapi.repositories.UserEntityRepository;

public class UserResponse {
    public String name;
    public String city;
    public String role;
    public String avatar;

    public UserResponse(String name, String city, String role, String avatar) {
        this.name = name;
        this.city = city;
        this.role = role;
        this.avatar = avatar;
    }

    public UserResponse(User user) {
        this.name = user.getId().login;
        this.city = user.getCity();
        this.role = user.getRole();
        this.avatar = user.getAvatar();
    }

    public UserResponse(UserEntity user){
        this.name = user.getLogin();
        this.city = user.getCity();
        this.role = user.getRole().getRole();
        this.avatar = user.getAvatar();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
