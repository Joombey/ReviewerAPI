package com.example.reviewerapi.controllers;

import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.models.dto.ReviewDto;
import com.example.reviewerapi.models.dto.embedable.ReviewId;
import com.example.reviewerapi.models.requests.ReportRequestModel;
import com.example.reviewerapi.services.ReviewService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ControllerAdvice
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @ApiResponse(description = "Добавление нового отзыва", responseCode = "200",
    content = @Content(schema = @Schema(implementation = ReviewId.class, description = "Id созданного отзыва")))
    @PostMapping(path = "/new-review")
    public ResponseEntity newReview(@RequestBody ReviewDto reviewDto){
        ReviewId id = null;
        try {
            id = reviewService.createReview(reviewDto);
            return ResponseEntity.ok().body(id);
        } catch (NoUserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiResponse(description = "Спсиок отзывов от разных авторов за исключением указанного", responseCode = "200",
    content = @Content(schema = @Schema(implementation = ReviewDto.class, description = "Список отзывов не включающий указанного пользователя")))
    @GetMapping("/get-reviews-for/{user}")
    public ResponseEntity getReviewsForUser(@PathVariable("user") String login){
        return ResponseEntity.ok().body(reviewService.getAllReviewForUser(login));
    }

    @ApiResponse(description = "Получение списка всех отзывов", responseCode = "200",
    content = @Content(schema = @Schema(implementation = ReviewDto.class)))
    @GetMapping("/get-all")
    public ResponseEntity getAllReviews(){
        return ResponseEntity.ok().body(reviewService.getAllReviewAndUser());
    }

    @ApiResponse(description = "Пожаловаться на определённый отзыв, с указанием его Id", responseCode = "200",
            content = @Content(schema = @Schema(implementation = ReportRequestModel.class, description = "Список жалоб")))
    @PostMapping("/report")
    public ResponseEntity reportReview(
            @RequestParam("review_id") int reviewId
    ){
        System.out.println(reviewId);
        List<ReportRequestModel> newReportList = reviewService.report(reviewId);
        return ResponseEntity.ok().body(newReportList);
    }

    @RequestMapping(value = "/uploadTesting", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity uploadHandling(@RequestPart("files") List<MultipartFile> model){
        model.stream().map(m->{
            Path file = Path.of("G:\\ + " + m.getOriginalFilename());
            try {
                Files.write(file, m.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return m;
        }).toList();
        return ResponseEntity.ok().body("asd");
    };
}
