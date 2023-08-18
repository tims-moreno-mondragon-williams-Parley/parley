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
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}) // makes usernames unique in table.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // username has to be unique inside of table. no duplicate usernames allowed.
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean is_admin;

    @Column
    private String bio;

    @Column
    private String profile_pic;

    @Column
    private String banner_img;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Post> posts;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<LikeDislikePost> likeDislikePosts;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<LikeDislikeComment> likeDislikeComments;

    public User(User copy){
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        bio = copy.bio;
        profile_pic = copy.profile_pic;
        banner_img = copy.banner_img;
        is_admin = copy.is_admin;
    }
}
