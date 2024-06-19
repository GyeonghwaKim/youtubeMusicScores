package com.example.youtubeSheet.entity.dto;


import com.example.youtubeSheet.entity.Comment;
import com.example.youtubeSheet.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PostDto {


    private Long id;
    private String title;
    private String content;
    private SiteUser author;
    private List<Comment> comments;
    private LocalDate createAt;
    private LocalDate updateAt;
}
