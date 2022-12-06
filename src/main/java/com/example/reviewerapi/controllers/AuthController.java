package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.UserAlreadyExistException;
import com.example.reviewerapi.requests.UserRequest;
import com.example.reviewerapi.responses.UserAndPermissionResponse;
import com.example.reviewerapi.models.embedable.UserId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/sign-in")
    public ResponseEntity<?> auth(@RequestBody UserId user){
        try {
            System.out.println(user.login);
            UserAndPermissionResponse userAndPermissionResponse = Mock.tryAuth(user);
            return ResponseEntity.ok().body(userAndPermissionResponse);
        } catch (NoUserFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequest userRequest){
        try {
            UserAndPermissionResponse newUser = Mock.trySignUp(userRequest);
            return ResponseEntity.ok().body(newUser);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/hello")
    public ResponseEntity hello(@ModelAttribute("hello") String string){
        System.out.println(string);
        return ResponseEntity.ok().body("abababaa");
    }
}
