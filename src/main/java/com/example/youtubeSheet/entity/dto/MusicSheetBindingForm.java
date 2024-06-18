package com.example.youtubeSheet.entity.dto;

import com.example.youtubeSheet.entity.annotation.Youtube;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicSheetBindingForm {

    @NotBlank(message = "제목은 필수 항목입니다")
    private String title;

    @Youtube
    @NotBlank(message = "url은 필수 항목입니다")
    private String url;


}
