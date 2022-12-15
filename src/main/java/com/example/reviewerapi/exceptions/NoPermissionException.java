package com.example.reviewerapi.exceptions;

public class NoPermissionException extends Exception{
    public NoPermissionException() {
        super("Нет доступа к действиям");
    }
}
