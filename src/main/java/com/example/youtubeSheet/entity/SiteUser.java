package com.example.youtubeSheet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Entity
public class SiteUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;

    @Column(unique=true)
    private String email;



    public SiteUser() {
    }


}
