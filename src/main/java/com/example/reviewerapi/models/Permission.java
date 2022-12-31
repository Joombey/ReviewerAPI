package com.example.reviewerapi.models;

import com.example.reviewerapi.models.entities.PermissionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Permission {
    public Permission(){}
    Permission(Builder builder){
        role = builder.role;
        reviewBlockAccess = builder.reviewBlockAccess;
        roleChangerAccess = builder.roleChangerAccess;
        reviewMakerAccess = builder.reviewMakerAccess;
        profileAccess = builder.profileAccess;
    }

    public static final int VISIBLE = 0;
    public static final int INVISIBLE = 8;

    public static final Permission MODER = new Permission.Builder()
            .role("moder")
                    .profileAccess(Permission.ACCESS)
                    .reviewMakerAccess(Permission.ACCESS)
                    .reviewBlockAccess(Permission.ACCESS)
                    .build();
    public static final Permission ADMIN= new Permission.Builder()
                    .role("admin")
                    .profileAccess(Permission.ACCESS)
                    .reviewMakerAccess(Permission.ACCESS)
                    .roleChangerAccess(Permission.ACCESS)
                    .build();
    public static final Permission USER = new Permission.Builder()
                    .role("user")
                    .profileAccess(Permission.ACCESS)
                    .reviewMakerAccess(Permission.ACCESS)
                    .build();
    public static final Permission UNAUTHORIZED = new Permission.Builder()
                    .role("unauthorized")
                    .build();

    public static final boolean ACCESS = true;
    public static final boolean DENY = false;

    private String role;
    private boolean reviewMakerAccess;
    private boolean profileAccess;
    private boolean reviewBlockAccess;
    private boolean roleChangerAccess;

    public Permission(String role, boolean reviewMakerAccess, boolean profileAccess, boolean reviewBlockAccess, boolean roleChangerAccess) {
        this.role = role;
        this.reviewMakerAccess = reviewMakerAccess;
        this.profileAccess = profileAccess;
        this.reviewBlockAccess = reviewBlockAccess;
        this.roleChangerAccess = roleChangerAccess;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getReviewMakerAccess() {
        return reviewMakerAccess;
    }

    public void setReviewMakerAccess(boolean reviewMakerAccess) {
        this.reviewMakerAccess = reviewMakerAccess;
    }

    public boolean getProfileAccess() {
        return profileAccess;
    }

    public void setProfileAccess(boolean profileAccess) {
        this.profileAccess = profileAccess;
    }

    public boolean getReviewBlockAccess() {
        return reviewBlockAccess;
    }

    public void setReviewBlockAccess(boolean reviewBlockAccess) {
        this.reviewBlockAccess = reviewBlockAccess;
    }

    public boolean getRoleChangerAccess() {
        return roleChangerAccess;
    }

    public void setRoleChangerAccess(boolean roleChangerAccess) {
        this.roleChangerAccess = roleChangerAccess;
    }

    public static Permission getPermissionByRoleName(String newRole) {
        switch (newRole){
            case "moder": return MODER;
            case "user": return USER;
            case "admin": return ADMIN;
            default: return null;
        }
    }


    public static class Builder{
        String role;
        boolean reviewMakerAccess;
        boolean profileAccess;
        boolean reviewBlockAccess;
        boolean roleChangerAccess;
        public Builder(){
            role = "unauthorized";
            reviewBlockAccess = Permission.DENY;
            reviewMakerAccess = Permission.DENY;
            profileAccess = Permission.ACCESS;
            roleChangerAccess = Permission.DENY;
        }

        public Builder role(String role){
            this.role = role;
            return this;
        }
        public Builder reviewBlockAccess(boolean access){
            this.reviewBlockAccess = access;
            return this;
        }

        public Builder reviewMakerAccess(boolean access){
            this.reviewMakerAccess = access;
            return this;
        }

        public Builder roleChangerAccess(boolean access){
            this.roleChangerAccess = access;
            return this;
        }

        public Builder profileAccess(boolean access){
            this.profileAccess = access;
            return this;
        }

        public Permission build(){
            return new Permission(this);
        }
    }

    @JsonIgnore
    public PermissionEntity getPermissionEntityInstance(){
        return new PermissionEntity(
                role,
                reviewMakerAccess,
                profileAccess,
                reviewBlockAccess,
                roleChangerAccess
        );
    }
}
