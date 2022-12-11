package com.example.reviewerapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Target;

@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @OneToOne(targetEntity = ReviewEntity.class)
    @Column(name = "id", nullable = false)
    private int reviewId;

    @Column(name = "report_amount", nullable = false)
    private int reportAmt;
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReportAmt() {
        return reportAmt;
    }

    public void setReportAmt(int reportAmt) {
        this.reportAmt = reportAmt;
    }
}
