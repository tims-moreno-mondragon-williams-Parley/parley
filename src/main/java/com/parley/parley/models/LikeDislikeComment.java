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
    @Id

    @Column(nullable = false)
    private String like_dislike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public LikeDislikeComment(String like_dislike) {
        this.like_dislike = like_dislike;
    }
}
