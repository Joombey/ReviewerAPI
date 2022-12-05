package com.example.reviewerapi.models;

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
