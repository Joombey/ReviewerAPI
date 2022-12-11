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

    @Column(nullable = false)
    private Integer report_amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReviewEntity getReport_id() {
        return review;
    }

    public void setReport_id(ReviewEntity report_id) {
        this.review = report_id;
    }

    public Integer getReport_amount() {
        return report_amount;
    }

    public void setReport_amount(Integer report_amount) {
        this.report_amount = report_amount;
    }
}
