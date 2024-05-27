package com.example.youtubeSheet.repository;


import com.example.youtubeSheet.entity.Sheet;
import com.example.youtubeSheet.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SheetsRepository extends JpaRepository<Sheet,Long> {
    List<Sheet> findBySiteUser(SiteUser siteUser);


}
