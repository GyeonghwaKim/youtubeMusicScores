package com.example.youtubeSheet.entity;


import com.example.youtubeSheet.entity.dto.SheetSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Sheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;


    @ManyToOne
    private SiteUser siteUser;

    public Sheet() {
    }

    public Sheet(SheetSaveRequestDto sheetSaveRequestDto) {
        this.title = sheetSaveRequestDto.getTitle();
        this.url = sheetSaveRequestDto.getUrl();
    }


}
