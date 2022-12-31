package com.example.reviewerapi.models.responses;

import com.example.reviewerapi.models.requests.ReportRequestModel;
import com.example.reviewerapi.models.dto.ReviewDto;

import java.util.List;

public class ReportsWithReviewsResponse {
    List<ReportRequestModel> reports;
    List<ReviewDto> reviewDtoList;

    public ReportsWithReviewsResponse(List<ReportRequestModel> reports, List<ReviewDto> reviewDtoList) {
        this.reports = reports;
        this.reviewDtoList = reviewDtoList;
    }

    public List<ReportRequestModel> getReports() {
        return reports;
    }

    public void setReports(List<ReportRequestModel> reports) {
        this.reports = reports;
    }

    public List<ReviewDto> getReviews() {
        return reviewDtoList;
    }

    public void setReviews(List<ReviewDto> reviewDtoList) {
        this.reviewDtoList = reviewDtoList;
    }
}
