package com.example.reviewerapi.requests;

public class Report {
    public Report(int id){
        this.id = id;
        reportAmt = 1;
    }

    public Report(int id, int reportAmt) {
        this.id = id;
        this.reportAmt = reportAmt;
    }

    public int id;
    public int reportAmt;
}
