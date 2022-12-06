package com.example.reviewerapi.requests;

import com.example.reviewerapi.models.embedable.UserId;

public class UserRequest {
    public UserId id;

    public String city;
    public String avatar;

    public UserRequest(UserId id, String city, String role, String avatar) {
        this.id = id;
        this.city = city;
        this.avatar = avatar;
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
}
