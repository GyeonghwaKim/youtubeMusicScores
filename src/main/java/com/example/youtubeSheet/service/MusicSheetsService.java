package com.example.youtubeSheet.service;


import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.dto.MusicSheetBindingForm;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.repository.MusicSheetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MusicSheetsService {

    private final MusicSheetsRepository musicSheetsRepository;

    public void save(MusicSheetBindingForm musicSheetBindingForm, SiteUser siteUser, LocalDate createLocalDate){
        MusicSheet musicSheet =new MusicSheet();
        musicSheet.setTitle(musicSheetBindingForm.getTitle());
        musicSheet.setUrl(musicSheetBindingForm.getUrl());
        musicSheet.setSiteUser(siteUser);
        musicSheet.setCreateLocalDate(createLocalDate);
        musicSheetsRepository.save(musicSheet);

    }

    public MusicSheet getSheet(Long id){
        Optional<MusicSheet> sheet=this.musicSheetsRepository.findById(id);
        return sheet.get();
    }


    public List<MusicSheet> showSheetList(SiteUser siteUser){

        return musicSheetsRepository.findBySiteUser(siteUser);
    }



    public void delete(MusicSheet musicSheet){

        this.musicSheetsRepository.delete(musicSheet);
    }

    public void modify(MusicSheet musicSheet, String title){

        musicSheet.setTitle(title);
        this.musicSheetsRepository.save(musicSheet);

    }

}
