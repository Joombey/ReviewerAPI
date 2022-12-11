package com.example.reviewerapi.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException() {
        super("Пользователь уже существует");
    }
}
