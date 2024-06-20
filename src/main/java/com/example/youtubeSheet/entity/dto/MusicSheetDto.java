package com.example.youtubeSheet.entity.dto;

import com.example.youtubeSheet.entity.SiteUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MusicSheetDto {
    private Long id;
    private String title;
    private String url;
    private SiteUserDto siteUser;
    private LocalDate createLocalDate;
}
