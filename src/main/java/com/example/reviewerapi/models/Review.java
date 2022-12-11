package com.example.reviewerapi.models;

import com.example.reviewerapi.models.embedable.ReviewId;

public class Review {
    public ReviewId id;
    public String reviewTitle;
    public String creationTime;
    public String item;
    public String paragraphs;

    public Review(ReviewId id, String reviewTitle, String creationTime, String item, String paragraphs) {
        this.id = id;
        this.reviewTitle = reviewTitle;
        this.creationTime = creationTime;
        this.item = item;
        this.paragraphs = paragraphs;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
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
