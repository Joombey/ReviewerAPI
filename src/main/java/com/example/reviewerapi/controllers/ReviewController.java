package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.requests.Report;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
public class ReviewController {

    @ApiResponse(description = "Добавление нового отзыва", responseCode = "200",
    content = @Content(schema = @Schema(implementation = ReviewId.class, description = "Id созданного отзыва")))
    @PostMapping(path = "/new-review")
    public ResponseEntity newReview(@RequestBody Review review){
        ReviewId id = Mock.setNewReview(review);
        return ResponseEntity.ok().body(id);
    }

    @ApiResponse(description = "Спсиок отзывов от разных авторов за исключением указанного", responseCode = "200",
    content = @Content(schema = @Schema(implementation = Review.class, description = "Список отзывов не включающий указанного пользователя")))
    @GetMapping("/get-reviews-for/{user}")
    public ResponseEntity getReviewsForUser(@PathVariable("user") String login){
        return ResponseEntity.ok().body(Mock.getAllReviewExceptUser(login));
    }

    @ApiResponse(description = "Получение списка всех отзывов", responseCode = "200",
    content = @Content(schema = @Schema(implementation = Review.class)))
    @GetMapping("/get-all")
    public ResponseEntity getAllReviews(){
        return ResponseEntity.ok().body(Mock.reviewList);
    }

    @ApiResponse(description = "Пожаловаться на определённый отзыв, с указанием его Id", responseCode = "200",
            content = @Content(schema = @Schema(implementation = Report.class, description = "Список жалоб")))
    @PostMapping("/report")
    public ResponseEntity reportReview(
            @RequestParam("review_id") int reviewId
    ){
        List<Report> newReportList = Mock.addReport(reviewId);
        return ResponseEntity.ok().body(newReportList);
    }
}
