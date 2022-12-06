package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.requests.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @PostMapping(path = "/new-review")
    public ResponseEntity newReview(@RequestBody Review review){
        ReviewId id = Mock.setNewReview(review);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/get-reviews-for/{user}")
    public ResponseEntity getReviewsForUser(@PathVariable("user") String login){
        return ResponseEntity.ok().body(Mock.getAllReviewExceptUser(login));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllReviews(){
        return ResponseEntity.ok().body(Mock.reviewList);
    }

    @PostMapping("/report")
    public ResponseEntity reportReview(
            @RequestParam("review_id") int reviewId
    ){
        List<Report> newReportList = Mock.addReport(reviewId);
        return ResponseEntity.ok().body(newReportList);
    }
}
