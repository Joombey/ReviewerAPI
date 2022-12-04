package com.example.reviewerapi.models;

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

    public transient String role;
    public transient boolean reviewMakerAccess;
    public transient boolean profileAccess;
    public transient boolean reviewBlockAccess;
    public transient boolean roleChangerAccess;

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

    public PermissionModel getPermissionEntityInstance(){
        return new PermissionModel(
                role,
                reviewMakerAccess,
                profileAccess,
                reviewBlockAccess,
                roleChangerAccess
        );
    }
}
