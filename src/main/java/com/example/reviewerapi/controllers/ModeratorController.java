package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.requests.Report;
import com.example.reviewerapi.responses.ReportsWithReviewsResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/moderator")
public class ModeratorController {
    @PostMapping("/review-block")
    public ResponseEntity reviewBlock(
            @RequestParam("report_id") int reportId,
            @RequestParam("moder") String moderatorName
    ){
        try {
            ReportsWithReviewsResponse reportsWithReviewsResponse = Mock.blockReview(reportId, moderatorName);
            return ResponseEntity.ok().body(reportsWithReviewsResponse);
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/report-deny/{reportId}")
    public ResponseEntity reportDeny(
            @PathVariable("reportId") int reportId,
            @RequestParam("moder") String moderName
    ){
        List<Report> newReportList = Mock.deny(reportId, moderName);
        return ResponseEntity.ok().body(newReportList);
    }

    @GetMapping("/report-list")
    public ResponseEntity getReportList(@RequestParam("moder") String moderName){
        try {
            return ResponseEntity.ok().body(Mock.getResponseList(moderName));
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
