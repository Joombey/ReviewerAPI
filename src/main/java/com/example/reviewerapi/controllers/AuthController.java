package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.UserAlreadyExistException;
import com.example.reviewerapi.models.User;
import com.example.reviewerapi.models.UserAndPermission;
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
            UserAndPermission userAndPermission = Mock.tryAuth(user);
            return ResponseEntity.ok().body(userAndPermission);
        } catch (NoUserFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user){
        try {
            UserAndPermission newUser = Mock.trySignUp(user);
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
