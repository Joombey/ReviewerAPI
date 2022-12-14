package com.example.reviewerapi.controllers;

import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.models.requests.ReportRequestModel;
import com.example.reviewerapi.models.responses.ReportsWithReviewsResponse;
import com.example.reviewerapi.services.ModeratorService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RestController
@RequestMapping("/moderator")
public class ModeratorController {

    @Autowired
    ModeratorService moderatorService;

    @ApiResponses({
            @ApiResponse(description = "Возврщает новый список жалоб вместе со списком отзывов на которые были произведены жалобы, после блокировки удаления отзыва и жалобы", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReportsWithReviewsResponse.class, description = "Список жалоб с соответствующими отзывами"))),
            @ApiResponse(description = "Возвращается сообщение о том что на указанные действия у пользователя нет прав", responseCode = "403")
    })
    @PostMapping("/review-block")
    public ResponseEntity reviewBlock(
            @RequestParam("report_id") int reportId,
            @RequestParam("moder") String moderatorName
    ){
        try {
            ReportsWithReviewsResponse reportsWithReviewsResponse = moderatorService.blockReview(reportId, moderatorName);
            return ResponseEntity.ok().body(reportsWithReviewsResponse);
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(description = "Возврщает новый список жалоб, после отклонения (удаления) жалобы", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ReportRequestModel.class, description = "Список жалоб"))),
            @ApiResponse(description = "Возвращается сообщение о том что на указанные действия у пользователя нет прав", responseCode = "403")
    })
    @PostMapping("/report-deny/{reportId}")
    public ResponseEntity reportDeny(
            @PathVariable("reportId") int reportId,
            @RequestParam("moder") String moderName
    ){
        try {
            List<ReportRequestModel> newReportList = moderatorService.denyReport(reportId, moderName);
            return ResponseEntity.ok().body(newReportList);
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(description = "Возврщает список жалоб", responseCode = "200",
            content = @Content(schema = @Schema(implementation = ReportRequestModel.class, description = "Список жалоб"))),
            @ApiResponse(description = "Возвращается сообщение о том что на указанные действия у пользователя нет прав", responseCode = "403")
    })
    @GetMapping("/report-list")
    public ResponseEntity getReportList(@RequestParam("moder") String moderName){
        try {
            return ResponseEntity.ok().body(moderatorService.getReportList(moderName));
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
