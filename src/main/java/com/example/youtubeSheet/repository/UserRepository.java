package com.example.youtubeSheet.repository;

import com.example.youtubeSheet.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long> {


    Optional<SiteUser> findByUsername(String username);

    List<SiteUser> findAll();
}
