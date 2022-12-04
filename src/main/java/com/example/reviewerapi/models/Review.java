package com.example.reviewerapi.models;

import com.example.reviewerapi.models.embedable.ReviewId;

import java.util.Objects;

public class Review {
    public ReviewId id;
    public String item;
    public String paragraphs;

    public Review(ReviewId id, String item, String paragraphs) {
        this.id = id;
        this.item = item;
        this.paragraphs = paragraphs;
    }
}
