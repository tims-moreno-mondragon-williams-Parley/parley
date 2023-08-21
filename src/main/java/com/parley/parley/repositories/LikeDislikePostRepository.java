package com.parley.parley.repositories;

import com.parley.parley.models.LikeDislikeComment;
import com.parley.parley.models.LikeDislikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDislikePostRepository extends JpaRepository<LikeDislikePost, Long> {
    List<LikeDislikePost> findAllByPost_Id(Long id);

    List<LikeDislikePost> findAllByUser_Id(Long id);
}
