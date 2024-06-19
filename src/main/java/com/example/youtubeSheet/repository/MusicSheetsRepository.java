package com.example.youtubeSheet.repository;


import com.example.youtubeSheet.entity.MusicSheet;
import com.example.youtubeSheet.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MusicSheetsRepository extends JpaRepository<MusicSheet,Long> {
    List<MusicSheet> findBySiteUser(SiteUser siteUser);


}
