package com.example.youtubeSheet.service;


import com.example.youtubeSheet.dto.SheetSaveForm;
import com.example.youtubeSheet.dto.SheetSaveRequestDto;
import com.example.youtubeSheet.entity.Sheet;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.repository.SheetsRepository;
import com.example.youtubeSheet.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class SheetsService {

    private final SheetsRepository sheetsRepository;

    private final UserRepository userRepository;

    public void save(SheetSaveForm sheetSaveForm, SiteUser siteUser){


        Sheet sheet=new Sheet();
        sheet.setTitle(sheetSaveForm.getTitle());
        sheet.setUrl(sheetSaveForm.getUrl());
        sheet.setSiteUser(siteUser);
        sheetsRepository.save(sheet);

    }

    public Sheet getSheet(Long id){
        Optional<Sheet> sheet=this.sheetsRepository.findById(id);
        return sheet.get();
    }

    public String showUrl(Sheet sheet){
        String url=sheet.getUrl();
        return url;
    }

    public List<Sheet> showSheetList(SiteUser siteUser){

        return sheetsRepository.findBySiteUser(siteUser);
    }

    public void delete(Sheet sheet){

        this.sheetsRepository.delete(sheet);
    }

    public void modify(Sheet sheet,String title){

        sheet.setTitle(title);
        this.sheetsRepository.save(sheet);

    }

    public String showTitle(Sheet sheet) {
        return sheet.getTitle();
    }
}
