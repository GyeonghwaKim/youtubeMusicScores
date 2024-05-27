package com.example.youtubeSheet.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SheetTitleForm {


    @NotBlank
    private String title;
}
