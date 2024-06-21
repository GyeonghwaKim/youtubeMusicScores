package com.example.youtubeSheet.repository;

import com.example.youtubeSheet.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Override
    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTitleContainingOrAuthorUsername(String title,String username,Pageable pageable);
}
