package com.example.reviewerapi.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "report_id")
    private int report_id;

    @Column(nullable = false)
    private int report_amount;

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getReport_amount() {
        return report_amount;
    }

    public void setReport_amount(int report_amount) {
        this.report_amount = report_amount;
    }


}
