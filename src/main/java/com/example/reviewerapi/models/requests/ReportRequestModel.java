package com.example.reviewerapi.models.requests;

public class ReportRequestModel {
    public ReportRequestModel(int id){
        this.id = id;
        reportAmt = 1;
    }

    public ReportRequestModel(int id, int reportAmt) {
        this.id = id;
        this.reportAmt = reportAmt;
    }

    public int id;
    public int reportAmt;
}
