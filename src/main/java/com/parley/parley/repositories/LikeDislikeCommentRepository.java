package com.parley.parley.repositories;

import com.parley.parley.models.LikeDislikeComment;
import com.parley.parley.models.LikeDislikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDislikeCommentRepository extends JpaRepository<LikeDislikeComment, Long> {
    List<LikeDislikeComment> findAllByComment_Id(Long id);

    List<LikeDislikeComment> findAllByUser_Id(Long id);
}
