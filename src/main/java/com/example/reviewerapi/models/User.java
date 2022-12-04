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
}
