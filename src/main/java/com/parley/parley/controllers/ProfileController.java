package com.parley.parley.controllers;

import org.springframework.ui.Model;
import com.parley.parley.models.User;
import com.parley.parley.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import com.parley.parley.models.Post;
import com.parley.parley.repositories.UserRepository;
import com.parley.parley.repositories.PostRepository;

import java.util.List;


@Controller
public class ProfileController {

    private final UserService userService;
//    private final UserRepository userDao;
    private final PostRepository postDao;

    @Autowired
    public ProfileController(UserService userService, PostRepository postDao, UserRepository userDao) {
        this.userService = userService;
        this.postDao = postDao;
//        this.userDao = userDao;
    }


    @GetMapping("/profile")
    public String viewUserProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> posts = postDao.findAllByUser_Id(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return "users/profile";
    }

    @GetMapping("/profile/update")
    public String viewUpdateProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);
        return "users/update-profile";
    }


    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        updatedUser.setId(user.getId());
        updatedUser.setPassword(user.getPassword());
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setBio(updatedUser.getBio());
        user.setProfile_pic(updatedUser.getProfile_pic());
        user.setBanner_img(updatedUser.getBanner_img());

        userService.updateUserProfile(updatedUser);

        return "redirect:/profile";
    }


// Commented out code is for followers feature if wanting to add to project.
//    @PostMapping("/{userId}/follow/{followedId}")
//    public String followUser(@PathVariable Long userId, @PathVariable Long followedId, Model model) {
//        try {
//            User followedUser = userService.followUser(userId, followedId);
//
//            if (followedUser != null) {
//                System.out.println("You're now following " + followedId);
//            } else {
//                String errorMessage = ("An error has occurred. Please try again later.");
//                model.addAttribute("errorMessage", errorMessage);
//            }
//        } catch (Exception e) {
//            String errorMessage = "An error occurred while processing your request. Please try again later.";
//            model.addAttribute("errorMessage", errorMessage);
//            System.out.println(errorMessage);
//        }
//        return "redirect:/profile/" + userId;
//    }
//
//    @PostMapping("/{userId}/unfollow/{followedId}")
//    public String unfollowUser(@PathVariable Long userId, @PathVariable Long followedId, Model model) {
//        try {
//            User unfollowedUser = userService.unfollowUser(userId, followedId);
//
//            if (unfollowedUser != null) {
//                System.out.println("You've successfully unfollowed " + followedId);
//            } else {
//                System.out.println("An error has occurred. Please try again later.");
//            }
///
//        } catch (Exception e) {
//            String errorMessage = ("An error occurred while processing your request. Please try again later.");
//            model.addAttribute("errorMessage", errorMessage);
//            System.out.println(errorMessage);
//        }
//        return "redirect:/profile/" + userId;
//    }
}

