package com.example.youtubeSheet.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id",nullable = false)
    private SiteUser author;

}
