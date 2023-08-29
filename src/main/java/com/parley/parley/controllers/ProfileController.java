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
        updatedUser.set_admin(user.is_admin());
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setBio(updatedUser.getBio());
        user.setProfile_pic(updatedUser.getProfile_pic());
        user.setBanner_img(updatedUser.getBanner_img());

        userService.updateUserProfile(updatedUser);

        return "redirect:/profile";
    }
}

