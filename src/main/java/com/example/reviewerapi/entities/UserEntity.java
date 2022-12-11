package com.example.reviewerapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "city",nullable = true)
    private String city;
    @Column(name = "avatar")
    private String avatar;

    @ManyToOne(targetEntity = PermissionEntity.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "role", referencedColumnName = "role", table = "roles")
    private PermissionEntity role;
}
