package com.example.youtubeSheet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){


        return "newHome";    }


}
