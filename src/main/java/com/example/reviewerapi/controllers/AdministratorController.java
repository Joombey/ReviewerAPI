package com.example.reviewerapi.controllers;

//import com.example.reviewerapi.Mock;
import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.models.responses.UserAndPermissionResponse;
import com.example.reviewerapi.models.dto.UserDto;
import com.example.reviewerapi.services.AdminService;
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
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    AdminService adminService;

    @ApiResponses({
            @ApiResponse(description = "Возврщает пользователя с новыми правами, если имеется доступ", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class, description = "Пользователь и его разрешения"))),
            @ApiResponse(description = "Возвращается сообщение о том что прав на действия нет", responseCode = "403"),
            @ApiResponse(description = "Возвращается сообщение о том такого пользователья нет", responseCode = "400")
    })
    @RequestMapping("/change-role/{userLogin}")
    public ResponseEntity changeRole(
            @PathVariable("userLogin") String userLogin,
            @RequestParam(name = "role") String role,
            @RequestParam(name = "admin") String admin
    ){
        try {
            UserAndPermissionResponse userAndPermissionResponse = adminService.changeRole(userLogin, role, admin);
            return ResponseEntity.ok().body(userAndPermissionResponse);
        } catch (NoUserFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(description = "Возврщает список пользователей, если имеется доступ", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class, description = "Список пользователей"))),
            @ApiResponse(description = "Возвращается сообщение о том что прав на действия нет", responseCode = "403"),
            @ApiResponse(description = "Возвращается сообщение о том такого пользователья нет", responseCode = "400")
    })
    @RequestMapping("/ban/{userLogin}")
    public ResponseEntity ban(
            @PathVariable("userLogin") String user,
            @RequestParam("admin") String admin
    ){
        try {
            List<UserAndPermissionResponse> newUserList = adminService.banUser(user, admin);
            return ResponseEntity.badRequest().body(newUserList);
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @ApiResponses({
            @ApiResponse(description = "Возврщает список пользователей, если имеется доступ", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDto.class, description = "Пользователь и его разрешения"))),
            @ApiResponse(description = "Возвращается сообщение о том что прав на действия нет", responseCode = "400")
    })
    @GetMapping("/user-list")
    public ResponseEntity getUsersForRoleChanger(
            @RequestParam("admin") String admin
    ){
//        return ResponseEntity.ok().body(Mock.getUsersBeside(admin));
        try {
            return ResponseEntity.ok().body(adminService.getUserList(admin));
        } catch (NoPermissionException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @RequestMapping("/init")
    public ResponseEntity init(){
        adminService.init();
        return ResponseEntity.ok().body("asd");
    }
}
