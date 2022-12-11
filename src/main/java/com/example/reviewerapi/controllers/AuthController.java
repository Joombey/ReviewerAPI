package com.example.reviewerapi.controllers;

//import com.example.reviewerapi.Mock;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.UserAlreadyExistException;
import com.example.reviewerapi.requests.UserRequest;
import com.example.reviewerapi.responses.ReportsWithReviewsResponse;
import com.example.reviewerapi.responses.UserAndPermissionResponse;
import com.example.reviewerapi.models.embedable.UserId;
import com.example.reviewerapi.services.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiResponses({
            @ApiResponse(description = "Аутентификация пользователья, после которой возвращаются данные о его роли и о нём самом", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserAndPermissionResponse.class, description = "Пользователь и его разрешения"))),
            @ApiResponse(description = "Возвращается сообщение о том что аутентификация не пройдена", responseCode = "400")
    })
    @PostMapping("/sign-in")
    public ResponseEntity<?> auth(@RequestBody UserId user){
        try {
            UserAndPermissionResponse userAndPermissionResponse = authService.auth(user);
            return ResponseEntity.ok().body(userAndPermissionResponse);
        } catch (NoUserFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(description = "Регистрация, после которой возвращаются данные о его роли и о нём самом", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserAndPermissionResponse.class, description = "Пользователь и его разрешения"))),
            @ApiResponse(description = "Возвращается сообщение о том что такой пользователь уже существует", responseCode = "400")
    })
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest){
        try {
            UserAndPermissionResponse newUser = authService.signUp(userRequest);
            return ResponseEntity.ok().body(newUser);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
