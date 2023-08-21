package com.parley.parley.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "comments_likes")
public class LikeDislikeComment {
    @EmbeddedId
    private CommentLikesId commentLikesId;

    @Column(nullable = false)
    private String likeDislike;

    public LikeDislikeComment(String likeDislike) {
        this.likeDislike = likeDislike;
    }
}
