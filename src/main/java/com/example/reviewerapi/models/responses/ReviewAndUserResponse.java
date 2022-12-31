package com.example.reviewerapi.models.responses;

import com.example.reviewerapi.models.dto.ReviewDto;

public class ReviewAndUserResponse {
    private ReviewDto reviewDto;
    private UserAndPermissionResponse user;

    public ReviewAndUserResponse(ReviewDto reviewDto, UserAndPermissionResponse user) {
        this.reviewDto = reviewDto;
        this.user = user;
    }

    public ReviewDto getReviewDto() {
        return reviewDto;
    }

    public void setReviewDto(ReviewDto reviewDto) {
        this.reviewDto = reviewDto;
    }

    public UserAndPermissionResponse getUser() {
        return user;
    }

    public void setUser(UserAndPermissionResponse user) {
        this.user = user;
    }
}
