package com.example.youtubeSheet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
public class SiteUser {
    //id, password, email

    @Id
    private UUID uuid;

    @Column(unique=true)
    private String username;

    private String password;


    @Column(unique=true)
    private String email;

    //private UserRole role;


    public SiteUser() {
    }


}
