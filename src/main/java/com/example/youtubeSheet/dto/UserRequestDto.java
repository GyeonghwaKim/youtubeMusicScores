package com.example.youtubeSheet.dto;


import com.example.youtubeSheet.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {



    @NotBlank
    private String userId;
    @NotBlank
    private String password;

    private UserRole role;

    public UserRequestDto() {
    }
}
