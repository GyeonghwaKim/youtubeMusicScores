package com.example.youtubeSheet.entity.exception;

public class EmptyTitleException extends RuntimeException {
    public EmptyTitleException(String message) {
        super(message);
    }
}
