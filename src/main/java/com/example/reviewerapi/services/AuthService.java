package com.example.reviewerapi.services;

import com.example.reviewerapi.models.entities.PermissionEntity;
import com.example.reviewerapi.models.entities.UserEntity;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.UserAlreadyExistException;
import com.example.reviewerapi.models.dto.embedable.UserId;
import com.example.reviewerapi.services.repositories.PermissionEntityRepository;
import com.example.reviewerapi.services.repositories.UserEntityRepository;
import com.example.reviewerapi.models.requests.SignUpUserRequestModel;
import com.example.reviewerapi.models.responses.UserAndPermissionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserEntityRepository userRepo;

    @Autowired
    PermissionEntityRepository permissionRepo;

    public UserAndPermissionResponse auth(UserId userId) throws NoUserFoundException {
        UserEntity user = userRepo.findByLoginAndPassword(userId.login, userId.password);
        if (user != null) {
            return new UserAndPermissionResponse(user);
        }throw new NoUserFoundException();
    }

    public UserAndPermissionResponse signUp(SignUpUserRequestModel userRequest) throws UserAlreadyExistException {
        if (!userRepo.existsById(userRequest.getId().login)){
            PermissionEntity newUserRole = permissionRepo.findById("user").get();
            return new UserAndPermissionResponse(userRepo.save(new UserEntity(
                    userRequest.getId().login,
                    userRequest.getId().password,
                    userRequest.city,
                    userRequest.avatar,
                    newUserRole
            )));
        } throw new UserAlreadyExistException();
    }
}
