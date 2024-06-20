package com.example.youtubeSheet.entity.dto;

import com.example.youtubeSheet.entity.Post;
import com.example.youtubeSheet.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private SiteUserDto author;
    private String content;
    private LocalDateTime createAt;
    private PostDto postDto;

}
