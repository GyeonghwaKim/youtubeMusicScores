package com.example.youtubeSheet.service;


import com.example.youtubeSheet.DataNotFoundException;
import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.dto.MusicSheetDto;
import com.example.youtubeSheet.entity.dto.SiteUserDto;
import com.example.youtubeSheet.repository.MusicSheetRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MusicSheetService {

    private final MusicSheetRepository musicSheetRepository;
    private final ModelMapper modelMapper;

    public void save(String title,String url, SiteUserDto siteUserDto){
        MusicSheetDto musicSheetDto =new MusicSheetDto();
        musicSheetDto.setTitle(title);
        musicSheetDto.setUrl(url);
        musicSheetDto.setSiteUser(siteUserDto);
        musicSheetDto.setCreateLocalDate(LocalDate.now());

        musicSheetRepository.save(of(musicSheetDto));

    }

    public MusicSheetDto getSheet(Long id){
        Optional<MusicSheet> musicSheet=this.musicSheetRepository.findById(id);

        if(musicSheet.isPresent()){
            return of(musicSheet.get());
        }else{
            throw new DataNotFoundException("MusicSheet Not Found");
        }

    }


    public List<MusicSheet> showSheetList(SiteUserDto siteUserDto){
        return musicSheetRepository.findBySiteUser_username(siteUserDto.getUsername());
    }



    public void delete(MusicSheetDto musicSheetDto){

        this.musicSheetRepository.delete(of(musicSheetDto));
    }

    public void modify(MusicSheetDto musicSheetDto, String title){
        musicSheetDto.setTitle(title);
        this.musicSheetRepository.save(of(musicSheetDto));

    }

    private MusicSheet of(MusicSheetDto musicSheetDto){
        return modelMapper.map(musicSheetDto,MusicSheet.class);
    }

    private MusicSheetDto of(MusicSheet musicSheet){
        return modelMapper.map(musicSheet,MusicSheetDto.class);
    }

}
