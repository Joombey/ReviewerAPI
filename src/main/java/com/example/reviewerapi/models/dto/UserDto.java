package com.example.reviewerapi.models.dto;

import com.example.reviewerapi.models.entities.UserEntity;

public class UserDto {
    public String name;
    public String city;
    public String role;
    public String avatar;

    public UserDto(UserEntity user){
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
