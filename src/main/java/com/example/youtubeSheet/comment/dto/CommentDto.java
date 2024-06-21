package com.example.youtubeSheet.comment.dto;

import com.example.youtubeSheet.post.dto.PostDto;
import com.example.youtubeSheet.user.dto.SiteUserDto;
import lombok.Getter;
import lombok.Setter;

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
