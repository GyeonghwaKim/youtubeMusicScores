package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.entity.Post;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.dto.CommentForm;
import com.example.youtubeSheet.entity.dto.PostDto;
import com.example.youtubeSheet.entity.dto.PostForm;
import com.example.youtubeSheet.entity.dto.SiteUserDto;
import com.example.youtubeSheet.service.PostService;
import com.example.youtubeSheet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Slf4j

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/list")
    public String postList(Model model,@RequestParam(value = "page",defaultValue = "0") int page){

        Page<Post> paging=this.postService.getList(page);
        model.addAttribute("paging",paging);
        return "postList";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(Model model,CommentForm commentForm,@PathVariable(name = "id")Long id){

        PostDto postDto=this.postService.getPost(id);
        model.addAttribute("post",postDto);
        return "postDetail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createPost(PostForm postForm)
    {
        return "postForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("postForm") PostForm postForm,
                             BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()) return "postForm";

        SiteUserDto siteUserDto=this.userService.getUser(principal.getName());
        this.postService.create(postForm.getTitle(),postForm.getContent(),siteUserDto);

        return "redirect:/post/list?page=0";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyPost(PostForm postForm, @PathVariable(name = "id") Long id,Principal principal){

        PostDto postDto=this.postService.getPost(id);

        if(!postDto.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        postForm.setTitle(postDto.getTitle());
        postForm.setContent(postDto.getContent());

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

        return "redirect:/post/detail/" + id;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id,Principal principal){

        PostDto postDto=this.postService.getPost(id);

        if(!postDto.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.postService.delete(postDto);

        return "redirect:/post/list?page=0";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String postVote(Principal principal, @PathVariable("id") Long id) {
        PostDto postDto = this.postService.getPost(id);
        SiteUserDto siteUserDto = this.userService.getUser(principal.getName());
        this.postService.vote(postDto, siteUserDto);
        return "redirect:/post/detail/"+id;
    }

}
