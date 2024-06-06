package com.example.youtubeSheet.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && auth.isAuthenticated()&&auth.getPrincipal()instanceof UserDetails){
            UserDetails userDetails=(UserDetails) auth.getPrincipal();
            log.info(userDetails.getUsername());
            model.addAttribute("username",userDetails.getUsername());
        }

        return "newHome";    }


}
