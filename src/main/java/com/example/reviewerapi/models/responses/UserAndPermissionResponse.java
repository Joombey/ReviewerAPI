package com.example.reviewerapi.models.responses;

import com.example.reviewerapi.models.dto.UserDto;
import com.example.reviewerapi.models.entities.PermissionEntity;
import com.example.reviewerapi.models.entities.UserEntity;

public class UserAndPermissionResponse {
    public UserDto user;
    public PermissionEntity permission;

    public UserAndPermissionResponse(UserEntity user) {
        this.permission = user.getRole();
        this.user = new UserDto(user);
    }
}
