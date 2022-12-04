package com.example.reviewerapi.models.embedable;

import java.util.Objects;

public class ReviewId {
    public int id;
    public String author;

    public ReviewId(int id, String author) {
        this.id = id;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewId reviewId = (ReviewId) o;
        return id == reviewId.id && author.equals(reviewId.author);
    }
}
