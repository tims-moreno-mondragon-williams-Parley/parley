package com.parley.parley.repositories;

import com.parley.parley.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);

    Post findByTitle(String title);

    List<Post> findAllByTitle(String title);

    List<Post> findAllByUser_Id(Long id);

    List<Post> findAllByTopic_Id(Long id);

    List<Post> findAllByTopic_IdAndPosition(Long id, String position);
}
