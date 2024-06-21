package com.example.youtubeSheet.musicSheets;

import com.example.youtubeSheet.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class MusicSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @ManyToOne
    private SiteUser siteUser;

    private LocalDate createLocalDate;

    public MusicSheet() {
    }



}
