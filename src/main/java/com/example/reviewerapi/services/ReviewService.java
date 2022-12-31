package com.example.reviewerapi.services;

import com.example.reviewerapi.models.dto.ReviewDto;
import com.example.reviewerapi.models.entities.ReportEntity;
import com.example.reviewerapi.models.entities.ReviewEntity;
import com.example.reviewerapi.models.entities.UserEntity;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.models.dto.embedable.ReviewId;
import com.example.reviewerapi.models.responses.ReviewAndUserResponse;
import com.example.reviewerapi.models.responses.UserAndPermissionResponse;
import com.example.reviewerapi.services.repositories.ReportEntityRepository;
import com.example.reviewerapi.services.repositories.ReviewEntityRepository;
import com.example.reviewerapi.services.repositories.UserEntityRepository;
import com.example.reviewerapi.models.requests.ReportRequestModel;
import com.example.reviewerapi.services.typeconverter.EntityTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReviewService {

    @Autowired
    ReviewEntityRepository reviewRepo;

    @Autowired
    UserEntityRepository userRepo;

    @Autowired
    ReportEntityRepository reportRepo;

    public List<ReviewDto> getAllReviewForUser(String userName){
        return EntityTypeConverter.toReviewDtoList(reviewRepo.findByAuthor_LoginNot(userName));
    }

    public List<ReviewDto> getAll(){
        return EntityTypeConverter.toReviewDtoList(reviewRepo.findAll());
    }

    public List<ReviewAndUserResponse> getAllReviewAndUser(){
        return EntityTypeConverter.toReviewAndUserResponse(reviewRepo.findAll());
    }

    public ReviewId createReview(ReviewDto reviewDto) throws NoUserFoundException{
        Optional<UserEntity> user = userRepo.findById(reviewDto.getId().author);
        if(user.isPresent()){
            ReviewEntity newReview = EntityTypeConverter.toReviewEntity(reviewDto, userRepo.findById(reviewDto.getId().author).get());
            return new ReviewId(reviewRepo.save(newReview).getId(), reviewDto.getId().author);
        } throw new NoUserFoundException();
    }

    public List<ReportRequestModel> report(int reviewId){
        ReportEntity report;
        if (!reportRepo.existsByReviewId(reviewId)) {
            report = new ReportEntity();
            report.setReview(reviewRepo.findById(reviewId).get());
            report.setReportAmt(1);
        } else{
            report = reportRepo.findByReviewId(reviewId);
            report.newReport();
        }
        reportRepo.save(report);
        return EntityTypeConverter.toReportList(reportRepo.findAll());
    }
}
