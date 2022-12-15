package com.example.reviewerapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private Integer id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    @Column(nullable = false, name = "report_amount")
    private Integer reportAmt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReviewEntity getReview() {
        return review;
    }

    public void setReview(ReviewEntity review) {
        this.review = review;
    }

    public Integer getReportAmt() {
        return reportAmt;
    }

    public void setReportAmt(Integer report_amount) {
        this.reportAmt = report_amount;
    }

    public void newReport(){
        this.reportAmt += 1;
    }
}
