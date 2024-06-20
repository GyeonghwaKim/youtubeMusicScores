package com.example.youtubeSheet.entity.dto;


import com.example.youtubeSheet.entity.Comment;
import com.example.youtubeSheet.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PostDto {


    private Long id;
    private String title;
    private String content;
    private SiteUserDto author;
    private List<Comment> commentList;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Set<SiteUserDto> voter;
}
