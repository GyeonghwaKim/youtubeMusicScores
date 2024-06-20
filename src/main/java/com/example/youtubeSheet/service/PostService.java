package com.example.youtubeSheet.service;


import com.example.youtubeSheet.DataNotFoundException;
import com.example.youtubeSheet.entity.Post;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.dto.PostDto;
import com.example.youtubeSheet.entity.dto.PostForm;
import com.example.youtubeSheet.entity.dto.SiteUserDto;
import com.example.youtubeSheet.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public Page<Post> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createAt"));
        Pageable pageable= PageRequest.of(page,10,Sort.by(sorts));
        return this.postRepository.findAll(pageable);
    }


    public void create(String title,String content, SiteUserDto siteUserDto) {
        PostDto postDto=new PostDto();
        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setAuthor(siteUserDto);
        postDto.setCreateAt(LocalDateTime.now());
        this.postRepository.save(of(postDto));
    }

    public PostDto getPost(Long id) {

        Optional<Post> post=this.postRepository.findById(id);

        if(post.isPresent()){
            return of(post.get());
        }else{
            throw new DataNotFoundException("Post Not Found");
        }
    }

    public void modify(PostDto postDto, String title, String content) {

        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setUpdateAt(LocalDateTime.now());

        this.postRepository.save(of(postDto));

    }

    public void delete(PostDto postDto) {
        this.postRepository.delete(of(postDto));
    }

    public void vote(PostDto postDto,SiteUserDto siteUserDto){
        postDto.getVoter().add(siteUserDto);
        this.postRepository.save(of(postDto));
    }

    private PostDto of(Post post){
        return modelMapper.map(post,PostDto.class);
    }

    private Post of(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }

}
