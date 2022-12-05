package com.example.reviewerapi.models;

public class UserAndPermission {
    public UserResponse user;
    public PermissionModel permission;

    public UserAndPermission(User user, Permission permission) {
        this.permission = permission.getPermissionEntityInstance();
        this.user = new UserResponse(user.id.login, user.city, permission.role, user.avatar);
    }

    public UserAndPermission(User user) {
        this.permission = Permission.USER.getPermissionEntityInstance();
        this.user = new UserResponse(user.id.login, user.city, permission.role, user.avatar);
    }
}
