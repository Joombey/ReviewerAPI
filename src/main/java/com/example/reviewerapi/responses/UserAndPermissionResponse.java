package com.example.reviewerapi.responses;

import com.example.reviewerapi.models.Permission;
import com.example.reviewerapi.models.User;

public class UserAndPermissionResponse {
    public UserResponse user;
    public Permission permission;

    public UserAndPermissionResponse(User user) {
        this.permission = Permission.getPermissionByRoleName(user.getRole());
        this.user = new UserResponse(user.getId().login, user.getCity(), permission.getRole(), user.getAvatar());
    }
}
