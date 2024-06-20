package com.example.youtubeSheet.service;


import com.example.youtubeSheet.DataNotFoundException;
import com.example.youtubeSheet.entity.Comment;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.dto.CommentDto;
import com.example.youtubeSheet.entity.dto.PostDto;
import com.example.youtubeSheet.entity.dto.SiteUserDto;
import com.example.youtubeSheet.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    public void create(PostDto postDto, String content, SiteUserDto siteUserDto) {

        CommentDto commentDto=new CommentDto();
        commentDto.setPostDto(postDto);
        commentDto.setAuthor(siteUserDto);
        commentDto.setContent(content);
        commentDto.setCreateAt(LocalDateTime.now());

        Comment comment=of(commentDto);

        this.commentRepository.save(comment);
    }


    private CommentDto of(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment of(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }

    public CommentDto getComment(Long id) {
        Optional<Comment> comment=this.commentRepository.findById(id);

        if(comment.isPresent()){
            return of(comment.get());
        }else{
            throw new DataNotFoundException("Comment Not Found");
        }
    }


    public void delete(CommentDto commentDto) {
        this.commentRepository.delete(of(commentDto));
    }
}
