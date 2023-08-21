package com.parley.parley.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "posts_likes")
public class LikeDislikePost {

    @EmbeddedId
    private PostLikesId postLikesId;

    @Column(nullable = false)
    private String likeDislike;

    public LikeDislikePost(String likeDislike){
        this.likeDislike = likeDislike;
    }

}
