package com.example.reviewerapi.entities;

import jakarta.persistence.*;
import jakarta.validation.Constraint;

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @Column(name = "review_id", nullable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "author", referencedColumnName = "login", foreignKey = @ForeignKey(name="asdsad"))
    private String author;

}
