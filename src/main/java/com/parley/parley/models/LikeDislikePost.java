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
    @Id

    @Column(nullable = false)
    private String like_dislike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public LikeDislikePost(String like_dislike){
        this.like_dislike = like_dislike;
    }

}
