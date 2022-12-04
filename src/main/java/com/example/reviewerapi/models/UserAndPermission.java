package com.example.reviewerapi.models;

public class UserAndPermission {
    public User user;
    public PermissionModel permission;

    public UserAndPermission(User user, Permission permission) {
        this.user = user;
        this.permission = permission.getPermissionEntityInstance();
    }

    public UserAndPermission(User user) {
        this.user = user;
        this.permission = Permission.USER.getPermissionEntityInstance();
    }
}
