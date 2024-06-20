package com.example.youtubeSheet.service;

import com.example.youtubeSheet.entity.SiteUser;
import com.example.youtubeSheet.entity.dto.PostForm;
import com.example.youtubeSheet.repository.PostRepository;
import com.example.youtubeSheet.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;


    private SiteUser testUser;

    @BeforeEach
    public void setup() {

        Optional<SiteUser> optionalUser = userRepository.findByUsername("test1");
        if (optionalUser.isPresent()) {
            testUser = optionalUser.get();
        } else {
            // 사용자가 존재하지 않으면 예외를 던집니다.
            throw new RuntimeException("Test user not found");
        }

    }

    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            PostForm postForm = new PostForm();
            postForm.setTitle("Test Title " + i);
            postForm.setContent("Test Content " + i);

            // Act
            //postService.create(postForm, testUser);
        }


    }

}