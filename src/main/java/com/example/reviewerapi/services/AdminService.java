package com.example.reviewerapi.services;

import com.example.reviewerapi.entities.UserEntity;
import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.models.Permission;
import com.example.reviewerapi.repositories.PermissionEntityRepository;
import com.example.reviewerapi.repositories.UserEntityRepository;
import com.example.reviewerapi.responses.UserAndPermissionResponse;
import com.example.reviewerapi.responses.UserResponse;
import com.example.reviewerapi.typeconverter.EntityTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    UserEntityRepository userRepo;

    @Autowired
    PermissionEntityRepository permissionRepo;

    public UserAndPermissionResponse changeRole(String user, String newRole, String admin) throws NoPermissionException, NoUserFoundException{
        Optional<UserEntity> adminInstance = userRepo.findById(admin);
        Optional<UserEntity> userInstance = userRepo.findById(user);

        if (adminInstance.isPresent() || !userInstance.isPresent()) {
            if(adminInstance.get().getRole().getRoleChangerAccess()){
                userInstance.get().setRole(permissionRepo.findById(newRole).get());
                return new UserAndPermissionResponse(userRepo.save(userInstance.get()));
            }throw new NoPermissionException();

        }throw new NoUserFoundException();
    }

    public List<UserResponse> banUser(String user, String admin) throws NoPermissionException{

        Optional<UserEntity> adminInstance = userRepo.findById(admin);

        if (adminInstance.isPresent() && adminInstance.get().getRole().getRoleChangerAccess()){
            userRepo.deleteById(user);
            return userRepo.findAll().stream().map(userInstance -> new UserResponse(userInstance)).toList();
        }throw new NoPermissionException();
    }

    public void init() {
        permissionRepo.save(Permission.ADMIN.getPermissionEntityInstance());
        permissionRepo.save(Permission.MODER.getPermissionEntityInstance());
        permissionRepo.save(Permission.USER.getPermissionEntityInstance());
        permissionRepo.save(Permission.UNAUTHORIZED.getPermissionEntityInstance());

        userRepo.save(new UserEntity("admin", "admin", "MOSCOW", "ASDASD", Permission.ADMIN.getPermissionEntityInstance()));
        userRepo.save(new UserEntity("user", "user", "MOSCOW", "ASDASD", Permission.USER.getPermissionEntityInstance()));
        userRepo.save(new UserEntity("moder", "moder", "MOSCOW", "ASDASD", Permission.MODER.getPermissionEntityInstance()));
    }

    public List<UserResponse> getUserList(String adminName) throws NoPermissionException {
        if (userRepo.existsByLoginAndRole_Role(adminName, "admin")){
            return EntityTypeConverter.toUserResponseList(userRepo.findAll());
        }throw new NoPermissionException();
    }
}
