package com.example.reviewerapi.responses;

import com.example.reviewerapi.entities.PermissionEntity;
import com.example.reviewerapi.entities.UserEntity;
import com.example.reviewerapi.models.Permission;
import com.example.reviewerapi.models.User;

public class UserAndPermissionResponse {
    public UserResponse user;
    public PermissionEntity permission;

    public UserAndPermissionResponse(UserEntity user) {
        this.permission = user.getRole();
        this.user = new UserResponse(user);
    }
}
