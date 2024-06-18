package com.example.youtubeSheet.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicSheetForm {

    @NotBlank(message = "제목은 필수 항목입니다")
    private String title;

    @NotBlank(message = "url은 필수 항목입니다")
    private String url;


}
