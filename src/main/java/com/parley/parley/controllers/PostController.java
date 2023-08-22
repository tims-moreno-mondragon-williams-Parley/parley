package com.parley.parley.controllers;

import com.parley.parley.models.*;
import com.parley.parley.repositories.*;
import org.hibernate.dialect.aggregate.PostgreSQLAggregateSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final UserRepository userDao;

    private final PostRepository postDao;

    private final CategoryRepository categoryDao;

    private final TopicRepository topicDao;

    private final CommentRepository commentDao;

    public PostController(UserRepository userDao, PostRepository postDao, CategoryRepository categoryDao, TopicRepository topicDao, CommentRepository commentDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.categoryDao = categoryDao;
        this.topicDao = topicDao;
        this.commentDao = commentDao;
    }

    public List<Category> getAllCategories(){
        return categoryDao.findAll();
    }

    public List<Topic> getAllTopics(){
        return topicDao.findAll();
    }

    @GetMapping({"/posts", "/posts/"})
    public String getPostHome(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Category> categories = getAllCategories();
        List<Topic> topics = getAllTopics();

        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("topicClicked", false);

        return "posts/posts";
    }

    @GetMapping({"/posts/{id}", "/posts/{id}/"})
    public String showTopicPosts(Model model, @PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Category> categories = getAllCategories();
        List<Topic> topics = getAllTopics();
        Topic topic = topicDao.findTopicById(id);
        List<Post> posts = postDao.findAllByTopic_Id(id);
        List<Comment> comments = new ArrayList<>();

        for (Post p : posts) {
            comments.addAll(commentDao.findAllByPost_Id(p.getId()));
        }

        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("posts", posts);
        model.addAttribute("viewedTopic", topic.getName());
        model.addAttribute("topicClicked", true);
        model.addAttribute("comments", comments);

        return "posts/posts";
    }
}
