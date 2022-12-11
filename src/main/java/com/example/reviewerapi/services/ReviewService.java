package com.example.reviewerapi.services;

import com.example.reviewerapi.entities.ReportEntity;
import com.example.reviewerapi.entities.ReviewEntity;
import com.example.reviewerapi.entities.UserEntity;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.repositories.ReportEntityRepository;
import com.example.reviewerapi.repositories.ReviewEntityRepository;
import com.example.reviewerapi.repositories.UserEntityRepository;
import com.example.reviewerapi.requests.Report;
import com.example.reviewerapi.typeconverter.EntityTypeConverter;
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

    public List<Review> getAllReviewForUser(String userName){
        return EntityTypeConverter.toReviewEntity(reviewRepo.findByAuthor_LoginNot(userName));
    }

    public List<Review> getAll(){
        return EntityTypeConverter.toReviewEntity(reviewRepo.findAll());
    }

    public ReviewId newReview(Review review) throws NoUserFoundException{
        Optional<UserEntity> user = userRepo.findById(review.getId().author);
        if(user.isPresent()){
            ReviewEntity newReview = EntityTypeConverter.toReviewEntity(review, userRepo.findById(review.getId().author).get());
            return new ReviewId(reviewRepo.save(newReview).getId(), review.getId().author);
        } throw new NoUserFoundException();
    }

    public List<Report> report(int reviewId){
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
