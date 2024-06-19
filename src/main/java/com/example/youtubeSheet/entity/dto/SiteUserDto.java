package com.example.youtubeSheet.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;


}
