package com.example.youtubeSheet.entity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicSheetTitleForm {


    @NotBlank
    private String title;
}
