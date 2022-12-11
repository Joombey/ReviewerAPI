package com.example.reviewerapi.exceptions;

public class NoUserFoundException extends Exception{
    public NoUserFoundException() {
        super("Пользователь не найден");
    }
}
