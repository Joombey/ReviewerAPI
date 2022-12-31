package com.example.reviewerapi.models.dto;

import com.example.reviewerapi.models.dto.embedable.ReviewId;

public class ReviewIdDto {
    public int id;
    public String author;

    public ReviewIdDto(int id, String author) {
        this.id = id;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewIdDto reviewId = (ReviewIdDto) o;
        return id == reviewId.id && author.equals(reviewId.author);
    }
}
