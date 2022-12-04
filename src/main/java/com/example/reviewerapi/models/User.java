package com.example.reviewerapi.models;

import com.example.reviewerapi.models.embedable.UserId;

public class User {
    public UserId id;

    public String city;
    public String role;

    public User(UserId id, String city, String role) {
        this.id = id;
        this.city = city;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
