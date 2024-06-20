package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.entity.dto.*;

import com.example.youtubeSheet.service.MusicSheetService;
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

    private final MusicSheetService musicSheetService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/saveMusicSheets")
    public String saveMusicSheets(MusicSheetForm musicSheetForm)
    {
        return "newSaveForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/saveMusicSheets")
    public String saveMusicSheets(@Valid @ModelAttribute("musicSheetForm") MusicSheetForm musicSheetForm,
                                  BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors())return "newSaveForm";

        SiteUserDto siteUserDto=this.userService.getUser(principal.getName());
        this.musicSheetService.save(musicSheetForm.getTitle(), musicSheetForm.getUrl(),
                siteUserDto);

        //참신한 리다이렉트생각하기
        return "redirect:/";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/musicSheets")
    public String showMusicSheets(@RequestParam(name = "id") Long id, Model model){

        MusicSheetDto musicSheetDto=this.musicSheetService.getSheet(id);

        model.addAttribute("url",musicSheetDto.getUrl());
        model.addAttribute("title",musicSheetDto.getTitle());
//고치세용
        LocalDate musicSheetDate=musicSheetDto.getCreateLocalDate();
        LocalDate currentDate= LocalDate.now();
        long countDays= ChronoUnit.DAYS.between(musicSheetDate,currentDate)+1;

        model.addAttribute("countDays",countDays);

        return "newYoutube";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/musicSheets/delete/{id}")
    public String deleteMusicSheets(@PathVariable(name = "id")Long id){

        MusicSheetDto musicSheetDto =this.musicSheetService.getSheet(id);
        this.musicSheetService.delete(musicSheetDto);
        return "redirect:/";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/musicSheets/modify/{id}")
    public String modifyMusicSheets(@PathVariable(name = "id")Long id,
                                    @Valid @ModelAttribute("modifyTitle") MusicSheetForm musicSheetForm, BindingResult bindingResult){



        MusicSheetDto musicSheetDto =this.musicSheetService.getSheet(id);
        this.musicSheetService.modify(musicSheetDto, musicSheetForm.getTitle());
        return "redirect:/musicSheets?id="+id;
    }


}
