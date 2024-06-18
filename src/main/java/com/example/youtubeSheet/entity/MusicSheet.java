package com.example.youtubeSheet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Setter
@Getter
@IdClass(MusicSheetId.class)
public class MusicSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @Id
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private SiteUser siteUser;

    private LocalDate createLocalDate;

    public MusicSheet() {
    }



}
