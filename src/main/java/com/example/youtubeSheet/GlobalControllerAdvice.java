package com.example.youtubeSheet;


import com.example.youtubeSheet.dto.SheetTitleForm;
import com.example.youtubeSheet.entity.Sheet;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.service.SheetsService;
import com.example.youtubeSheet.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalControllerAdvice {

    private final SheetsService sheetsService;

    private final UserService userService;


    @ModelAttribute("sheetLists")
    public List<Sheet> sheetList(Principal principal){


        if(principal ==null) return null;

        SiteUser siteUser=this.userService.findByUsername(principal.getName());

        return this.sheetsService.showSheetList(siteUser);

    }

    @ModelAttribute("modifyTitle")
    public SheetTitleForm sheetTitleForm(Model model){
        return new SheetTitleForm();
    }

}
