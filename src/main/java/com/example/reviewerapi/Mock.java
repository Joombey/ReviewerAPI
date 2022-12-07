package com.example.reviewerapi;

import com.example.reviewerapi.exceptions.NoPermissionException;
import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.UserAlreadyExistException;
import com.example.reviewerapi.exceptions.WrongRoleException;
import com.example.reviewerapi.models.*;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.models.embedable.UserId;
import com.example.reviewerapi.requests.Report;
import com.example.reviewerapi.requests.UserRequest;
import com.example.reviewerapi.responses.ReportsWithReviewsResponse;
import com.example.reviewerapi.responses.UserAndPermissionResponse;
import com.example.reviewerapi.responses.UserResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mock {

    public static List<User> userList = new ArrayList<>();
    public static List<Review> reviewList = new ArrayList<>();
    public static List<Report> reportList = new ArrayList<>();

    public static void init(){
        userList.add(new User(new UserId("admin", "admin"), "asd", Permission.ADMIN.getRole(), Permission.ADMIN.getRole()));
        userList.add(new User(new UserId("moder", "moder"), "asd", Permission.MODER.getRole(), Permission.MODER.getRole()));
        userList.add(new User(new UserId("user", "user"), "asd", Permission.USER.getRole(), Permission.USER.getRole()));
    }

    public static UserAndPermissionResponse tryAuth(UserId id) throws NoUserFoundException {
        List<User> tempList = userList.stream().filter(user -> user.getId().equals(id)).collect(Collectors.toList());
        if(tempList.size() != 0) {
            return new UserAndPermissionResponse(tempList.get(0));
        };
        throw new NoUserFoundException("UserRequest not found, or doesn't exist");
    }

    public static UserAndPermissionResponse trySignUp(UserRequest newUserRequest) throws UserAlreadyExistException{
        try {
            if (tryAuth(newUserRequest.id) != null) throw new UserAlreadyExistException("UserRequest already exists");
            return null;
        } catch (NoUserFoundException e){
            userList.add(new User(newUserRequest));
            return new UserAndPermissionResponse(userList.get(userList.size() - 1));
        }
    }

    public static ReviewId setNewReview(Review newReview){
        newReview.id.id = reviewList.size();
        reviewList.add(newReview);
        return newReview.id;
    }

    public static Review getReviewById(ReviewId reviewId){
        return reviewList.stream().filter(thisReview -> thisReview.id.equals(reviewId)).toList().get(0);
    };

    public static List<Review> getAllReviewExceptUser(String userLogin){
        return reviewList.stream().filter(review -> review.id.author != userLogin).toList();
    }

    public static List<Report> addReport(int reviewId){
        int id = findIdFromReport(reviewId);
        if(id == -1){
            reportList.add(new Report(reviewId));
        }else {
            Report report = reportList.get(id);
            report.reportAmt += 1;
            reportList.set(id, report);
        }
        return reportList;
    }

    public static ReportsWithReviewsResponse blockReview(int reportId, String moderName) throws NoPermissionException{
        User moder = findUser(moderName);
        if(Permission.getPermissionByRoleName(moder.getRole()).getReviewBlockAccess()) {
            removeReport(reportId);
            removeReview(reportId);
            return new ReportsWithReviewsResponse(reportList, reviewList);
        }
        throw new NoPermissionException("Нет прав на действие");
    }

    public static List<Report> deny(int reportId, String moderName) throws NoPermissionException{
        User moderUser = findUser(moderName);
        if(Permission.getPermissionByRoleName(moderName).getReviewBlockAccess()){
            removeReport(reportId);
            return reportList;
        } throw new NoPermissionException("Нет прав на действия");
    }

    public static List<UserResponse> banUser(UserId user){
        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getId().login == user.login){
                userList.remove(i);
                break;
            }
        }
        return userList.stream().map(userEl -> new UserResponse(userEl)).toList();
    }

    public static UserAndPermissionResponse changeRole(String user, String newRole, String admin) throws WrongRoleException, NoPermissionException {
        Permission permissionInstance = Permission.getPermissionByRoleName(newRole);
        User adminUser = findUser(admin);
        boolean hasAdminPermission = Permission.getPermissionByRoleName(adminUser.getRole()).getRoleChangerAccess();
        if(hasAdminPermission) {
            if (permissionInstance != null) {
                for (int i = 0; i < userList.size(); i++) {
                    if (userList.get(i).getId().login.equals(user)) {
                        User tempUser = userList.get(i);
                        tempUser.setRole(permissionInstance.getRole());
                        userList.set(i, tempUser);
                        return new UserAndPermissionResponse(userList.get(i));
                    }
                }
            }throw new WrongRoleException("Неправильно указанная роль");
        }throw new NoPermissionException("Нет прав на действие");
    }

    public static List<UserResponse> ban(String user, String admin) throws NoPermissionException, NoUserFoundException {
        User adminUser = findUser(admin);
        try {
            if(Permission.getPermissionByRoleName(adminUser.getRole()).getRoleChangerAccess() && adminUser != null){
                for(int i = 0; i < userList.size(); i++){
                    if (userList.get(i).getId().getLogin().equals(user)) {
                        userList.remove(i);
                        break;
                    }
                }
                List<UserResponse> resultList = new ArrayList<>();

                for(User userEl: userList){
                    resultList.add(new UserResponse(userEl));
                } return resultList;
            } throw new NoPermissionException("Нет прав на действие");
        } catch (NullPointerException e){
            throw new NoUserFoundException("Нет такого пользователя");
        }
    }

    public static User findUser(String userName){
        return userList.stream().filter(user-> user.getId().login.equals(userName)).toList().get(0);
    }

    public static List<UserResponse> getUsersBeside(String admin) {
        return userList.stream()
                .filter(user -> !user.getRole().equals(Permission.ADMIN.getRole()))
                .toList().stream().map(user -> new UserResponse(user)).toList();
    }

    public static List<UserResponse> getUserList() {
        return userList.stream().map(user -> new UserResponse(user)).toList();
    }

    public static List<Report> getResponseList(String moderName) throws NoPermissionException{
        User moder = findUser(moderName);
        if (moder != null){
            if (Permission.getPermissionByRoleName(moder.getRole()).getReviewBlockAccess()){
                return reportList;
            }
        }
        throw new NoPermissionException("Нет права на действие");
    }

    public int findReview(int reviewId){
        return reviewList.stream().filter(review-> review.getId().id == reviewId).toList().get(0).getId().id;
    }

    private static void removeReport(int reportId){
        for(int i = 0; i < reportList.size(); i++){
            if(reportList.get(i).id == reportId){
                reportList.remove(i);
                break;
            }
        }
    }

    private static int findIdFromReport(int reportId){
        for(int i = 0; i < reportList.size(); i++){
            if(reportList.get(i).id == reportId) return i;
        }
        return -1;
    }

    private static void removeReview(int reviewId){
        for(int i = 0; i < reviewList.size(); i++){
            if(reviewList.get(i).id.id == reviewId){
                reviewList.remove(i);
                break;
            }
        }
    }

}
