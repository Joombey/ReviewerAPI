package com.example.reviewerapi.services;

import com.example.reviewerapi.entities.ReportEntity;
import com.example.reviewerapi.entities.ReviewEntity;
import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.repositories.ReportEntityRepository;
import com.example.reviewerapi.repositories.ReviewEntityRepository;
import com.example.reviewerapi.repositories.UserEntityRepository;
import com.example.reviewerapi.requests.Report;
import com.example.reviewerapi.responses.ReportsWithReviewsResponse;
import com.example.reviewerapi.typeconverter.EntityTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeratorService {

    @Autowired
    ReportEntityRepository reportRepo;

    @Autowired
    ReviewEntityRepository reviewRepo;

    @Autowired
    UserEntityRepository userRepo;

    public ReportsWithReviewsResponse blockReview(int reportId, String moderatorName) throws NoPermissionException{
        if (userRepo.existByLoginAndRole(moderatorName, "moder")){
            reviewRepo.deleteById(reportId);
            return new ReportsWithReviewsResponse(
                    EntityTypeConverter.toReportList(reportRepo.findAll()),
                    EntityTypeConverter.toReviewEntity(reviewRepo.findByReportIsNotNull())
            );
        }throw new NoPermissionException();
    }

    public List<Report> denyReport(int reviewId, String moderatorName) throws NoPermissionException{
        if (userRepo.existByLoginAndRole(moderatorName, "moder")) {
            reportRepo.deleteByReviewId(reviewId);
            return EntityTypeConverter.toReportList(reportRepo.findAll());
        }throw new NoPermissionException();
    }

    public List<Report> getReportList(String moderatorName) throws NoPermissionException{
        if (userRepo.existByLoginAndRole(moderatorName, "moder")){
            return EntityTypeConverter.toReportList(reportRepo.findAll());
        } throw new NoPermissionException();
    }
}
