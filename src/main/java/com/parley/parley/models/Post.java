package com.parley.parley.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private String position;

    @Column
    private String topic_pic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Post(String title, String body, String position) {
        this.title = title;
        this.body = body;
        this.position = position;
    }

    public Post(String title, String body, String topic_position, String topic_pic) {
        this.title = title;
        this.body = body;
        this.position = topic_position;
        this.topic_pic = topic_pic;
    }

    public Post(String title, String body, String position, User user) {
        this.title = title;
        this.body = body;
        this.position = position;
        this.user = user;
    }

    public Post(String title, String body, String position, String topic_pic, User user) {
        this.title = title;
        this.body = body;
        this.position = position;
        this.topic_pic = topic_pic;
        this.user = user;
    }
}
