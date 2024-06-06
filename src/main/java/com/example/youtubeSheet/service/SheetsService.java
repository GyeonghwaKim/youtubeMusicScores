package com.example.youtubeSheet.service;


import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.dto.SheetSaveForm;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.repository.SheetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class SheetsService {

    private final SheetsRepository sheetsRepository;

    public void save(SheetSaveForm sheetSaveForm, SiteUser siteUser, LocalDate createLocalDate){
        MusicSheet musicSheet =new MusicSheet();
        musicSheet.setTitle(sheetSaveForm.getTitle());
        musicSheet.setUrl(sheetSaveForm.getUrl());
        musicSheet.setSiteUser(siteUser);
        musicSheet.setCreateLocalDate(createLocalDate);
        sheetsRepository.save(musicSheet);

    }

    public MusicSheet getSheet(Long id){
        Optional<MusicSheet> sheet=this.sheetsRepository.findById(id);
        return sheet.get();
    }


    public List<MusicSheet> showSheetList(SiteUser siteUser){

        return sheetsRepository.findBySiteUser(siteUser);
    }



    public void delete(MusicSheet musicSheet){

        this.sheetsRepository.delete(musicSheet);
    }

    public void modify(MusicSheet musicSheet, String title){

        musicSheet.setTitle(title);
        this.sheetsRepository.save(musicSheet);

    }

}
