package com.example.youtubeSheet.entity.dto;

import com.example.youtubeSheet.entity.Post;
import com.example.youtubeSheet.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private SiteUser author;
    private String content;
    private LocalDate createAt;
    private PostDto postDto;

}
