package com.parley.parley.controllers;

import com.parley.parley.models.*;
import com.parley.parley.repositories.*;
import org.hibernate.dialect.aggregate.PostgreSQLAggregateSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final UserRepository userDao;

    private final PostRepository postDao;

    private final CategoryRepository categoryDao;

    private final TopicRepository topicDao;

    private final CommentRepository commentDao;

    private final LikeDislikePostRepository likeDislikeDao;

    public PostController(UserRepository userDao, PostRepository postDao, CategoryRepository categoryDao, TopicRepository topicDao, CommentRepository commentDao, LikeDislikePostRepository likeDislikeDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.categoryDao = categoryDao;
        this.topicDao = topicDao;
        this.commentDao = commentDao;
        this.likeDislikeDao = likeDislikeDao;
    }

    public List<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    public List<Topic> getAllTopics(){
        return topicDao.findAll();
    }

    @GetMapping({"/posts", "/posts/"})
    public String getPostHome(Model model){
        List<Category> categories = getAllCategories();
        List<Topic> topics = getAllTopics();

        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("topicClicked", false);

        return "posts/posts";
    }

    @GetMapping({"/posts/{id}", "/posts/{id}/"})
    public String showTopicPosts(Model model, @PathVariable Long id){
        Post formPost = new Post();
        Comment formComment = new Comment();
        LikeDislikePost likeDislikePost = new LikeDislikePost();
        List<Category> categories = getAllCategories();
        List<Topic> topics = getAllTopics();
        Topic topic = topicDao.findTopicById(id);
        List<Post> posts = postDao.findAllByTopic_Id(id);
        List<Comment> comments = new ArrayList<>();


        for (Post p : posts) {
            comments.addAll(commentDao.findAllByPost_Id(p.getId()));
        }

        model.addAttribute("likeDislikePost", likeDislikePost);
        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("posts", posts);
        model.addAttribute("viewedTopic", topic);
        model.addAttribute("topicClicked", true);
        model.addAttribute("comments", comments);
        model.addAttribute("formPost", formPost);
        model.addAttribute("formComment", formComment);

        return "posts/posts";
    }

    @PostMapping({"/posts/{id}/create", "/posts/{id}/create/"})
    public String createPost(@PathVariable Long id, @ModelAttribute Post post){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        post.setTopic(topicDao.findTopicById(id));
        post.setId(null);
        postDao.save(post);
        String redirect = "redirect:/posts/" + id;
        return redirect;
    }

    @PostMapping({"/posts/{id}/{postId}/like-dislike", "/posts/{id}/{postId}/like-dislike/"})
    public String likeOrDislikePost(@PathVariable Long id, @PathVariable Long postId, @RequestParam(name="like") String like){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findPostById(postId);
        LikeDislikePost likeDislikePost = new LikeDislikePost();
        PostLikesId postLikesId = new PostLikesId(user, post);
        likeDislikePost.setLikeDislike(like);
        likeDislikePost.setPostLikesId(postLikesId);
        try {
            likeDislikeDao.save(likeDislikePost);
        } catch (Exception e){
            System.out.println(user.getId());
            System.out.println();
        }

        String redirect = "redirect:/posts/" + id;
        return redirect;
    }
}
