package com.parley.parley.controllers;

import com.parley.parley.models.*;
import com.parley.parley.repositories.*;
import org.hibernate.dialect.aggregate.PostgreSQLAggregateSupport;
import org.springframework.data.web.JsonPath;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        model = setupPostsPage(model);

        return "posts/posts";
    }

    @GetMapping({"/posts/{id}", "/posts/{id}/"})
    public String showTopicPosts(Model model, @PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findUserById(user.getId());
        Post formPost = new Post();
        Comment formComment = new Comment();
        List<LikeDislikePost> likeDislikePosts = likeDislikeDao.findAll();

        List<Category> categories = getAllCategories();
        List<Topic> topics = getAllTopics();
        Topic topic = topicDao.findTopicById(id);
        List<Post> posts = postDao.findAllByTopic_Id(id);
        List<Comment> comments = new ArrayList<>();

        HashMap<Long, String> userLikeMap = new HashMap<>();
        HashMap<Long, Long> likeCountMap = new HashMap<>();
        HashMap<Long, Long> dislikeCountMap = new HashMap<>();

        for (Post p : posts) {
            comments.addAll(commentDao.findAllByPost_Id(p.getId()));
            likeCountMap.put(p.getId(), likeDislikeDao.countLikeDislikePostByPostLikesId_PostAndLikeDislike(p, "l"));
            dislikeCountMap.put(p.getId(), likeDislikeDao.countLikeDislikePostByPostLikesId_PostAndLikeDislike(p, "d"));
            if(likeDislikeDao.findLikeDislikePostByPostLikesId_PostAndPostLikesId_User(p, user) != null){
                userLikeMap.put(p.getId(), likeDislikeDao.findLikeDislikePostByPostLikesId_PostAndPostLikesId_User(p, user).getLikeDislike());
            }
        }

        model.addAttribute("likeDislikePosts", likeDislikePosts);
        model.addAttribute("userLikeMap", userLikeMap);
        model.addAttribute("likeCountMap", likeCountMap);
        model.addAttribute("dislikeCountMap", dislikeCountMap);
        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("posts", posts);
        model.addAttribute("viewedTopic", topic);
        model.addAttribute("topicClicked", true);
        model.addAttribute("comments", comments);
        model.addAttribute("formPost", formPost);
        model.addAttribute("formComment", formComment);
        model.addAttribute("user", user);

        return "posts/posts";
    }

    @PostMapping({"/posts/{id}/create", "/posts/{id}/create/"})
    public String createPost(@PathVariable Long id, @ModelAttribute Post post){

        System.out.println(post.getPosition());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        post.setTopic(topicDao.findTopicById(id));
        post.setId(null);
        if(post.getTitle() != null && !post.getTitle().equals("") && post.getBody() != null && !post.getBody().equals("")){
            try {
                postDao.save(post);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
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
            if(likeDislikeDao.findLikeDislikePostByPostLikesId_PostAndPostLikesId_User(post, user) != null && Objects.equals(likeDislikeDao.findLikeDislikePostByPostLikesId_PostAndPostLikesId_User(post, user).getLikeDislike(), likeDislikePost.getLikeDislike())){
                likeDislikeDao.delete(likeDislikePost);
            } else {
                likeDislikeDao.save(likeDislikePost);
            }
        } catch (Exception e){
            System.out.println(user.getId());
            System.out.println();
        }

        String redirect = "redirect:/posts/" + id;
        return redirect;
    }

    @PostMapping({"/posts/create-category", "/posts/create-category/"})
    public String createNewCategory(@RequestParam(name="new_category_name") String name, Model model){
        Category category = new Category();
        category.setName(name);
        try {
            categoryDao.save(category);
            return "redirect:/posts";
        } catch (Exception e) {
            model = setupPostsPage(model);
            model.addAttribute("catCreteError", "Error creating category.");
            return "posts/posts";
        }
    }


    @PostMapping({"/posts/create-topic", "/posts/create-topic/"})
    public String createNewTopic(@RequestParam(name="new_topic_name") String name, @RequestParam(name="category_id") Long id, Model model){
        Topic topic = new Topic();
        Category category = categoryDao.findCategoriesById(id);
        topic.setName(name);
        topic.setCategory(category);
        if(topicDao.findTopicByNameAndCategory_Id(topic.getName(), id) == null) {
            try {
                topicDao.save(topic);
                return "redirect:/posts";
            } catch (Exception e) {
                model = setupPostsPage(model);
                model.addAttribute("topicCreteError", "Error creating new topic under " + category.getName() + "'s category.");
                return "posts/posts";
            }
        } else {
            model = setupPostsPage(model);
            model.addAttribute("topicCreteError", "Error creating new topic under " + category.getName() + "'s category.");
            return "posts/posts";
        }
    }

    @PostMapping({"/posts/{id}/{postId}/comment", "/posts/{id}/{postId}/comment/"})
    public String postComment(@RequestParam(name="comment-body") String body, @PathVariable Long id, @PathVariable Long postId, Model model){
        Post post = postDao.findPostById(postId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment();
        comment.setBody(body);
        comment.setPost(post);
        comment.setUser(user);
        System.out.println("comment = " + comment.getBody());
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("post.getTitle() = " + post.getTitle());
        try {
            System.out.println("Trying to save new comment");
            commentDao.save(comment);
            String redirect = "redirect:/posts/" + id;
            return redirect;
        } catch (Exception e){
            System.out.println("Error occurred during comment writing.");
            model = setupPostsPage(model);
            model.addAttribute("commentError", "Could not add Comment on " + post.getTitle() + "!");
            return "posts/posts";
        }
    }


    public Model setupPostsPage(Model model){
        List<Category> categories = getAllCategories();
        List<Topic> topics = getAllTopics();

        model.addAttribute("categories", categories);
        model.addAttribute("topics", topics);
        model.addAttribute("topicClicked", false);

        return model;
    }
}
