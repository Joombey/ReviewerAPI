package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.embedable.ReviewId;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @PostMapping(path = "/new-review")
    public ResponseEntity newReview(Review review){
        ReviewId id = Mock.setNewReview(review);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/get-reviews-for/{user}")
    public ResponseEntity getReviewsForUser(@PathVariable("user") String login){
        return ResponseEntity.ok().body(Mock.getAllReviewExceptUser(login));
    }

    @GetMapping("/hello")
    public ResponseEntity hello(String hello){
        System.out.println(hello);
        return ResponseEntity.ok().body("kaka");
    }
}
