package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.entity.dto.UserCreateRequestDto;

import com.example.youtubeSheet.entity.dto.ProfileDto;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {


    private final UserService userService;



    @GetMapping("/signup")
    @PreAuthorize("isAnonymous()")
    public String join(Model model){
        model.addAttribute("signupForm",new UserCreateRequestDto());
        return "newSignupForm";
    }

    @PostMapping("/signup")
    @PreAuthorize("isAnonymous()")
    public String createUser(
            @Valid @ModelAttribute("signupForm") UserCreateRequestDto userCreateRequestDto,
            BindingResult bindingResult){


        if(bindingResult.hasErrors()) return "newSignupForm";

        if(!userCreateRequestDto.getPassword()
                .equals(userCreateRequestDto.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword","passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");


            return "newSignupForm";
        }

        try{
            this.userService.createUser(userCreateRequestDto);
        }catch (DataIntegrityViolationException e
        ){
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 등록된 사용자입니다");
            return "newSignupForm";
        }

        catch (Exception e){
            e.printStackTrace();
            bindingResult.rejectValue("signupFailed",e.getMessage());
            return "newSignupForm";
        }


        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){

        return "newLoginForm";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        ProfileDto profileDto =new ProfileDto();
        profileDto.setUsername(principal.getName());
        profileDto.setEmail(this.userService.findByUsername(principal.getName()).getEmail());
        model.addAttribute("user", profileDto);

        return "newProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String profile(@Valid @ModelAttribute("user") ProfileDto profileDto,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "newProfile";
        }

        if(!profileDto.getConfirmPassword().equals(profileDto.getPassword())){

            bindingResult.rejectValue("confirmPassword","passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");

            return "newProfile";
        }


        try{
            this.userService.changeEmail(profileDto);
            this.userService.changePassword(profileDto);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("changeEmail","이미 등록된 이용자입니다");
            return "newProfile";}


            return "redirect:/";


    }




}
