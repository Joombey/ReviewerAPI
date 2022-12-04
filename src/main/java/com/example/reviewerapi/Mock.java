package com.example.reviewerapi;

import com.example.reviewerapi.exceptions.NoUserFoundException;
import com.example.reviewerapi.exceptions.UserAlreadyExistException;
import com.example.reviewerapi.models.Permission;
import com.example.reviewerapi.models.Review;
import com.example.reviewerapi.models.User;
import com.example.reviewerapi.models.UserAndPermission;
import com.example.reviewerapi.models.embedable.ReviewId;
import com.example.reviewerapi.models.embedable.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mock {

    static List<User> userList = new ArrayList<>();
    static List<Review> reviewList = new ArrayList<>();
    Permission permission;


    public static UserAndPermission tryAuth(UserId id) throws NoUserFoundException {
        List<User> tempList = userList.stream().filter(user -> user.id.equals(id)).collect(Collectors.toList());
        if(tempList.size() != 0) {
            return new UserAndPermission(tempList.get(0));
        };
        throw new NoUserFoundException("User not found, or doesn't exist");
    }

    public static UserAndPermission trySignUp(User newUser) throws UserAlreadyExistException{
        try {
            System.out.println(newUser.id);
            if (tryAuth(newUser.id) != null) throw new UserAlreadyExistException("User already exists");
            return null;
        } catch (NoUserFoundException e){
            userList.add(newUser);
            return new UserAndPermission(newUser);
        }
    }

    public static ReviewId setNewReview(Review newReview){
        newReview.id.id = reviewList.size();
        reviewList.add(newReview);
        return newReview.id;
    }

    public static Review getReviewById(ReviewId reviewId){
        return reviewList.stream().filter(thisReview->{
            return thisReview.id.equals(reviewId);
        }).toList().get(0);
    };

    public static List<Review> getAllReviewExceptUser(String userLogin){
        return reviewList.stream().filter(review -> review.id.author != userLogin).toList();
    }
}
