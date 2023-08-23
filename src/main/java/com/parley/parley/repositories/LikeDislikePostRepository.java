package com.parley.parley.repositories;

import com.parley.parley.models.LikeDislikePost;
import com.parley.parley.models.Post;
import com.parley.parley.models.PostLikesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeDislikePostRepository extends JpaRepository<LikeDislikePost, PostLikesId> {

    LikeDislikePost findLikeDislikePostByPostLikesId_PostAndLikeDislike(Post postLikesId_post, String likeDislike);
    Long countLikeDislikePostByPostLikesId_PostAndLikeDislike(Post postLikesId_post, String likeDislike);

}
