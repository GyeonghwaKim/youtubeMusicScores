package com.example.youtubeSheet.service;


import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.dto.MusicSheetBindingForm;
import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.repository.MusicSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MusicSheetService {

    private final MusicSheetRepository musicSheetRepository;

    public void save(MusicSheetBindingForm musicSheetBindingForm, SiteUser siteUser, LocalDate createLocalDate){
        MusicSheet musicSheet =new MusicSheet();
        musicSheet.setTitle(musicSheetBindingForm.getTitle());
        musicSheet.setUrl(musicSheetBindingForm.getUrl());
        musicSheet.setSiteUser(siteUser);
        musicSheet.setCreateLocalDate(createLocalDate);
        musicSheetRepository.save(musicSheet);

    }

    public MusicSheet getSheet(Long id){
        Optional<MusicSheet> sheet=this.musicSheetRepository.findById(id);
        return sheet.get();
    }


    public List<MusicSheet> showSheetList(SiteUser siteUser){

        return musicSheetRepository.findBySiteUser(siteUser);
    }



    public void delete(MusicSheet musicSheet){

        this.musicSheetRepository.delete(musicSheet);
    }

    public void modify(MusicSheet musicSheet, String title){

        musicSheet.setTitle(title);
        this.musicSheetRepository.save(musicSheet);

    }

}
