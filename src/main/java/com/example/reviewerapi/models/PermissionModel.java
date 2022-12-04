package com.example.reviewerapi.models;

public class PermissionModel {
    public String role;
    public Boolean reviewMakerAccess;
    public Boolean profileAccess;
    public Boolean reviewBlockAccess;
    public Boolean roleChangerAccess;

    public PermissionModel(
            String role,
            Boolean reviewMakerAccess,
            Boolean profileAccess,
            Boolean reviewBlockAccess,
            Boolean roleChangerAccess
    ) {
        this.role = role;
        this.reviewMakerAccess = reviewMakerAccess;
        this.profileAccess = profileAccess;
        this.reviewBlockAccess = reviewBlockAccess;
        this.roleChangerAccess = roleChangerAccess;
    }
}
