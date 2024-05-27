package com.example.youtubeSheet.entity.exception;

public class PasswordsNotSameException extends RuntimeException {
    public PasswordsNotSameException(String message) {
        super(message);
    }
}
