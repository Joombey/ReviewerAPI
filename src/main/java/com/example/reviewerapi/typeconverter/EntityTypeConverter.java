package com.example.reviewerapi.typeconverter;

import com.example.reviewerapi.entities.ReportEntity;
import com.example.reviewerapi.entities.ReviewEntity;
import com.example.reviewerapi.entities.UserEntity;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.repositories.ReviewEntityRepository;
import com.example.reviewerapi.repositories.UserEntityRepository;
import com.example.reviewerapi.requests.Report;
import com.example.reviewerapi.responses.UserResponse;

import java.util.List;

public class EntityTypeConverter {
    public static List<Report> toReportList(List<ReportEntity> reports){
        return reports.stream().map(reportEntity -> new Report(
                reportEntity.getReview().getId(), reportEntity.getReportAmt()
        )).toList();
    }

    public static List<Review> toReviewEntity(List<ReviewEntity> reviews){
        return reviews.stream().map(reviewEntity -> new Review(
                new ReviewId(reviewEntity.getId(), reviewEntity.getAuthor().getLogin()),
                reviewEntity.getReview_title(),
                reviewEntity.getCreation_time(),
                reviewEntity.getItem(),
                reviewEntity.getParagraphs()
        )).toList();
    }

    public static ReviewEntity toReviewEntity(Review review, UserEntity user){
        ReviewEntity entity = new ReviewEntity();
        entity.setAuthor(user);
        entity.setItem(review.getItem());
        entity.setParagraphs(review.getParagraphs());
        entity.setCreation_time(review.getCreationTime());
        entity.setReview_title(review.getReviewTitle());
        return entity;
    }

    public static List<UserResponse> toUserResponseList(List<UserEntity> userList){
        return userList.stream().map(userEntity -> new UserResponse(userEntity)).toList();
    }
}
