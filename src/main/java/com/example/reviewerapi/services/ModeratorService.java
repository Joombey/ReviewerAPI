package com.example.reviewerapi.services;

import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.services.repositories.ReportEntityRepository;
import com.example.reviewerapi.services.repositories.ReviewEntityRepository;
import com.example.reviewerapi.services.repositories.UserEntityRepository;
import com.example.reviewerapi.models.requests.ReportRequestModel;
import com.example.reviewerapi.models.responses.ReportsWithReviewsResponse;
import com.example.reviewerapi.services.typeconverter.EntityTypeConverter;
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
                    EntityTypeConverter.toReviewDtoList(reviewRepo.findByReportIsNotNull())
            );
        }throw new NoPermissionException();
    }

    public List<ReportRequestModel> denyReport(int reviewId, String moderatorName) throws NoPermissionException{
        if (userRepo.existByLoginAndRole(moderatorName, "moder")) {
            reportRepo.deleteByReviewId(reviewId);
            return EntityTypeConverter.toReportList(reportRepo.findAll());
        }throw new NoPermissionException();
    }

    public List<ReportRequestModel> getReportList(String moderatorName) throws NoPermissionException{
        if (userRepo.existByLoginAndRole(moderatorName, "moder")){
            return EntityTypeConverter.toReportList(reportRepo.findAll());
        } throw new NoPermissionException();
    }
}
