package com.example.youtubeSheet.controller;


import com.example.youtubeSheet.dto.SheetSaveForm;
import com.example.youtubeSheet.dto.SheetSaveRequestDto;
import com.example.youtubeSheet.dto.SheetTitleForm;
import com.example.youtubeSheet.entity.Sheet;

import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.exception.EmptyTitleException;
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

@Slf4j
@RequestMapping
@RequiredArgsConstructor
@Controller
public class SheetController {

    private final SheetsService sheetsService;

    private final UserService userService;

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/saveSheets")
    public String saveSheets(Model model)
    {
        log.info("1");
        model.addAttribute("sheetForm",new SheetSaveRequestDto());
        return "newSaveForm";
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/saveSheets")
    public String saveSheets(@Valid @ModelAttribute("sheetForm") SheetSaveForm sheetSaveForm,
                             BindingResult bindingResult, Principal principal){


        if(bindingResult.hasErrors()){

            return "newSaveForm";
        }


        //유효성 검사도 해야해용
        SiteUser siteUser=this.userService.findByUsername(principal.getName());
        this.sheetsService.save(sheetSaveForm,siteUser);
        return "redirect:/";

    }


    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/sheetLists")
    public String showSheets(@RequestParam(name = "id") Long id, Model model){

        Sheet sheet=this.sheetsService.getSheet(id);

        String title=this.sheetsService.showTitle(sheet);
        String url=this.sheetsService.showUrl(sheet);
        model.addAttribute("url",url);
        model.addAttribute("title",title);

        return "newYoutube";
    }

   // @PreAuthorize("isAuthenticated()")
    @PostMapping("/sheetLists/delete/{id}")
    public String deleteSheets(@PathVariable(name = "id")Long id){
        Sheet sheet=this.sheetsService.getSheet(id);
        this.sheetsService.delete(sheet);
        return "redirect:/";

    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/sheetLists/modify/{id}")
    public String modifySheets(@PathVariable(name = "id")Long id,
                               @Valid @ModelAttribute("modifyTitle")SheetTitleForm sheetTitleForm,BindingResult bindingResult){

        if(bindingResult.hasErrors()) throw new EmptyTitleException("제목이 공백일 수 없습니다");
        Sheet sheet=this.sheetsService.getSheet(id);
        this.sheetsService.modify(sheet,sheetTitleForm.getTitle());
        return "redirect:/sheetLists?id="+id;
    }


}
