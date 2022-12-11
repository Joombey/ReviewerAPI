package com.example.reviewerapi.responses;

import com.example.reviewerapi.requests.Report;
import com.example.reviewerapi.models.Review;

import java.util.List;

public class ReportsWithReviewsResponse {
    List<Report> reports;
    List<Review> reviews;

    public ReportsWithReviewsResponse(List<Report> reports, List<Review> reviews) {
        this.reports = reports;
        this.reviews = reviews;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
