package com.example.youtubeSheet.service;


import com.example.youtubeSheet.entity.Post;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.dto.PostDto;
import com.example.youtubeSheet.entity.dto.PostForm;
import com.example.youtubeSheet.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public List<Post> getList(){
        return this.postRepository.findAll();
    }


    public void create(PostForm postForm, SiteUser siteUser) {
        Post post=new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setAuthor(siteUser);
        post.setCreateAt(LocalDate.now());
        this.postRepository.save(post);
    }

    public PostDto getPost(Long id) {

        Optional<Post> post=this.postRepository.findById(id);

        if(post.isPresent()){
            return of(post.get());
        }else{
            //예외던지기
            return null;
        }
    }

    public void modify(PostDto postDto, String title, String content) {

        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setUpdateAt(LocalDate.now());

        Post post=of(postDto);

        this.postRepository.save(post);

    }

    public void delete(PostDto postDto) {
        this.postRepository.delete(of(postDto));
    }

    private PostDto of(Post post){
        return modelMapper.map(post,PostDto.class);
    }

    private Post of(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }

}
