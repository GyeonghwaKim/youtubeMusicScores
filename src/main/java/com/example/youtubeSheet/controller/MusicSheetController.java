package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.dto.MusicSheetBindingForm;
import com.example.youtubeSheet.entity.dto.MusicSheetForm;
import com.example.youtubeSheet.entity.dto.MusicSheetTitleForm;

import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.service.SheetsService;
import com.example.youtubeSheet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@RequestMapping
@RequiredArgsConstructor
@Controller
public class MusicSheetController {

    private final SheetsService sheetsService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/saveMusicSheets")
    public String saveSheets(Model model)
    {
        model.addAttribute("musicSheetForm",new MusicSheetForm());
        return "newSaveForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/saveMusicSheets")
    public String saveSheets(@Valid @ModelAttribute("musicSheetForm") MusicSheetBindingForm musicSheetBindingForm,
                             BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors())return "newSaveForm";

        SiteUser siteUser=this.userService.findByUsername(principal.getName());
        LocalDate createLocalDate=LocalDate.now();
        this.sheetsService.save(musicSheetBindingForm,siteUser,createLocalDate);
        return "redirect:/";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/musicSheets")
    public String showSheets(@RequestParam(name = "id") Long id, Model model){

        MusicSheet musicSheet=this.sheetsService.getSheet(id);
        String title= musicSheet.getTitle();
        String url=musicSheet.getUrl();
        model.addAttribute("url",url);
        model.addAttribute("title",title);

        LocalDate musicSheetDate=musicSheet.getCreateLocalDate();
        LocalDate currentDate= LocalDate.now();
        long countDays= ChronoUnit.DAYS.between(musicSheetDate,currentDate)+1;

        model.addAttribute("countDays",countDays);

        return "newYoutube";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/musicSheets/delete/{id}")
    public String deleteSheets(@PathVariable(name = "id")Long id){
        MusicSheet musicSheet =this.sheetsService.getSheet(id);
        this.sheetsService.delete(musicSheet);
        return "redirect:/";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/musicSheets/modify/{id}")
    public String modifySheets(@PathVariable(name = "id")Long id,
                               @Valid @ModelAttribute("modifyTitle") MusicSheetTitleForm musicSheetTitleForm, BindingResult bindingResult){

        MusicSheet musicSheet =this.sheetsService.getSheet(id);
        this.sheetsService.modify(musicSheet, musicSheetTitleForm.getTitle());
        return "redirect:/musicSheets?id="+id;
    }


}
