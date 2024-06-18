package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.entity.dto.SignupForm;

import com.example.youtubeSheet.entity.dto.ProfileForm;
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

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {


    private final UserService userService;



    @GetMapping("/signup")
    @PreAuthorize("isAnonymous()")
    public String join(Model model){
        model.addAttribute("signupForm",new SignupForm());
        return "newSignupForm";
    }

    @PostMapping("/signup")
    @PreAuthorize("isAnonymous()")
    public String createUser(
            @Valid @ModelAttribute("signupForm") SignupForm signupForm,
            BindingResult bindingResult){


        if(bindingResult.hasErrors()) return "newSignupForm";

        if(!signupForm.getPassword()
                .equals(signupForm.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword","passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");


            return "newSignupForm";
        }

        try{
            this.userService.createUser(signupForm);
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
        ProfileForm profileForm =new ProfileForm();
        profileForm.setUsername(principal.getName());
        profileForm.setEmail(this.userService.findByUsername(principal.getName()).getEmail());
        model.addAttribute("user", profileForm);

        return "newProfile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile")
    public String profile(@Valid @ModelAttribute("user") ProfileForm profileForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "newProfile";
        }

        if(!profileForm.getConfirmPassword().equals(profileForm.getPassword())){

            bindingResult.rejectValue("confirmPassword","passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");

            return "newProfile";
        }


        try{
            this.userService.changeEmail(profileForm);
            this.userService.changePassword(profileForm);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("changeEmail","이미 등록된 이용자입니다");
            return "newProfile";}


            return "redirect:/";


    }




}
