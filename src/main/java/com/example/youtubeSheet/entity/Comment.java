package com.example.youtubeSheet.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private SiteUser author;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;



}
