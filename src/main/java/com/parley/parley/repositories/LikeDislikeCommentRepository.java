package com.parley.parley.repositories;

import com.parley.parley.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeDislikeCommentRepository extends JpaRepository<LikeDislikeComment, CommentLikesId> {

    Optional<LikeDislikeComment> findLikeDislikeCommentByCommentLikesId(CommentLikesId commentLikesId);

    List<LikeDislikeComment> findAllByCommentLikesId_User(User user);

    List<LikeDislikeComment> findAllByCommentLikesId_Comment(Comment comment);
}
