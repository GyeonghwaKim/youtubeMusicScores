package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.entity.Post;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.dto.PostDto;
import com.example.youtubeSheet.entity.dto.PostForm;
import com.example.youtubeSheet.service.PostService;
import com.example.youtubeSheet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/list")
    public String postList(Model model){
        List<Post> postList=this.postService.getList();
        model.addAttribute("postList",postList);
        return "postList";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(Model model,@PathVariable(name = "id")Long id){
        //postid로 찾고
        PostDto post=this.postService.getPost(id);
        model.addAttribute("post",post);
        return "postDetail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createPost(Model model)
    {
        model.addAttribute("postForm",new PostForm());
        return "postForm";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("postForm") PostForm postForm,
                             BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()) return "postForm";

        SiteUser siteUser=this.userService.findByUsername(principal.getName());
        this.postService.create(postForm,siteUser);

        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyPost(Model model, @PathVariable(name = "id") Long id,Principal principal){

        PostDto postDto=this.postService.getPost(id);

        if(!postDto.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        PostForm postForm=new PostForm();
        postForm.setTitle(postDto.getTitle());
        postForm.setContent(postDto.getContent());

        model.addAttribute("postForm",postForm);

        return "postForm";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyPost(@Valid @ModelAttribute("postForm")PostForm postForm,BindingResult bindingResult,
                             @PathVariable(name = "id") Long id,Principal principal){
        if(bindingResult.hasErrors()) return "postForm";

        PostDto postDto=this.postService.getPost(id);

        if(!postDto.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.postService.modify(postDto,postForm.getTitle(),postForm.getContent());

        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id,Principal principal){

        PostDto postDto=this.postService.getPost(id);
        if(!postDto.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.postService.delete(postDto);

        return "redirect:/";
    }


}
