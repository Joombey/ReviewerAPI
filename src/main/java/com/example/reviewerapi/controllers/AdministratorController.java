package com.example.reviewerapi.controllers;

import com.example.reviewerapi.Mock;
import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.WrongRoleException;
import com.example.reviewerapi.responses.UserAndPermissionResponse;
import com.example.reviewerapi.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    @RequestMapping("/change-role/{userLogin}")
    public ResponseEntity changeRole(
            @PathVariable("userLogin") String userLogin,
            @RequestParam(name = "role") String role,
            @RequestParam(name = "admin") String admin
    ){
        try {
            UserAndPermissionResponse userAndPermissionResponse = Mock.changeRole(userLogin, role, admin);
            return ResponseEntity.ok().body(userAndPermissionResponse);
        } catch (WrongRoleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @RequestMapping("/ban/{userLogin}")
    public ResponseEntity ban(
            @PathVariable("userLogin") String user,
            @RequestParam("admin") String admin
    ){
        try {
            List<UserResponse> newUserList = Mock.ban(user, admin);
            return ResponseEntity.badRequest().body(newUserList);
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (NoUserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user-list")
    public ResponseEntity getUsersForRoleChanger(
            @RequestParam("admin") String admin
    ){
//        return ResponseEntity.ok().body(Mock.getUsersBeside(admin));
        return ResponseEntity.ok().body(Mock.getUserList());
    }
}
