package com.example.reviewerapi.services.typeconverter;

import com.example.reviewerapi.models.entities.ReportEntity;
import com.example.reviewerapi.models.entities.ReviewEntity;
import com.example.reviewerapi.models.entities.UserEntity;
import com.example.reviewerapi.models.dto.ReviewDto;
import com.example.reviewerapi.models.dto.embedable.ReviewId;
import com.example.reviewerapi.models.requests.ReportRequestModel;
import com.example.reviewerapi.models.responses.ReviewAndUserResponse;
import com.example.reviewerapi.models.responses.UserAndPermissionResponse;

import java.util.List;

public class EntityTypeConverter {
    public static List<ReportRequestModel> toReportList(List<ReportEntity> reports){
        return reports.stream().map(reportEntity -> new ReportRequestModel(
                reportEntity.getReview().getId(), reportEntity.getReportAmt()
        )).toList();
    }

    public static List<ReviewDto> toReviewDtoList(List<ReviewEntity> reviews){
        return reviews.stream().map(reviewEntity -> new ReviewDto(
                new ReviewId(reviewEntity.getId(), reviewEntity.getAuthor().getLogin()),
                reviewEntity.getReview_title(),
                reviewEntity.getCreation_time(),
                reviewEntity.getItem(),
                reviewEntity.getParagraphs()
        )).toList();
    }

    public static List<ReviewAndUserResponse> toReviewAndUserResponse(List<ReviewEntity> reviews){
        return reviews.stream().map(
                reviewEntity -> new ReviewAndUserResponse(
                        new ReviewDto(
                                new ReviewId(reviewEntity.getId(), reviewEntity.getAuthor().getLogin()),
                                reviewEntity.getReview_title(),
                                reviewEntity.getCreation_time(),
                                reviewEntity.getItem(),
                                reviewEntity.getParagraphs()),
                        new UserAndPermissionResponse(reviewEntity.getAuthor())
                )).toList();
    }

    public static ReviewEntity toReviewEntity(ReviewDto reviewDto, UserEntity user){
        ReviewEntity entity = new ReviewEntity();
        entity.setAuthor(user);
        entity.setItem(reviewDto.getItem());
        entity.setParagraphs(reviewDto.getParagraphs());
        entity.setCreation_time(reviewDto.getCreationTime());
        entity.setReview_title(reviewDto.getReviewTitle());
        return entity;
    }
}
