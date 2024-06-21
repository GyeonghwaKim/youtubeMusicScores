package com.example.youtubeSheet.post.dto;


import com.example.youtubeSheet.comment.Comment;
import com.example.youtubeSheet.user.dto.SiteUserDto;
import lombok.Getter;
import lombok.Setter;

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
