package com.example.youtubeSheet;


import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.dto.SheetTitleForm;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.service.SheetsService;
import com.example.youtubeSheet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {

    private final SheetsService sheetsService;

    private final UserService userService;



    @ModelAttribute("musicSheetList")
    public List<MusicSheet> sheetList(Principal principal){


        if(principal ==null) return null;

        SiteUser siteUser=this.userService.findByUsername(principal.getName());

        return this.sheetsService.showSheetList(siteUser);

    }

    @ModelAttribute("modifyTitle")
    public SheetTitleForm sheetTitleForm(Model model){
        return new SheetTitleForm();
    }
}