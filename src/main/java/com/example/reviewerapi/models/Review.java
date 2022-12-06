package com.example.reviewerapi.models;

import com.example.reviewerapi.models.embedable.ReviewId;

public class Review {
    public ReviewId id;
    public String item;
    public String paragraphs;

    public Review(ReviewId id, String item, String paragraphs) {
        this.id = id;
        this.item = item;
        this.paragraphs = paragraphs;
    }

    public ReviewId getId() {
        return id;
    }

    public void setId(ReviewId id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }
}
