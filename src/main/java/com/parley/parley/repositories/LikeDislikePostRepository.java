package com.parley.parley.repositories;

import com.parley.parley.models.LikeDislikePost;
import com.parley.parley.models.Post;
import com.parley.parley.models.PostLikesId;
import com.parley.parley.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeDislikePostRepository extends JpaRepository<LikeDislikePost, PostLikesId> {

    LikeDislikePost findLikeDislikePostByPostLikesId_PostAndLikeDislike(Post postLikesId_post, String likeDislike);
    Long countLikeDislikePostByPostLikesId_PostAndLikeDislike(Post postLikesId_post, String likeDislike);

    List<LikeDislikePost> findAllByPostLikesId_Post(Post postLikesId_post);
    LikeDislikePost findLikeDislikePostByPostLikesId_PostAndPostLikesId_User(Post postLikesId_post, User postLikesId_user);

}
