package com.example.youtubeSheet.dto;

import lombok.Getter;

@Getter
public class ErrorMessageDto {

    private String message;

    public ErrorMessageDto(String message) {
        this.message = message;
    }
}
