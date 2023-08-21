package com.parley.parley.repositories;

import com.parley.parley.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentById(Long id);

    List<Comment> findAllByUser_Id(Long id);

    List<Comment> findAllByPost_Id(Long id);

    List<Comment> findAllByPost_IdAndUser_Id(Long post_id, Long user_id);

}
