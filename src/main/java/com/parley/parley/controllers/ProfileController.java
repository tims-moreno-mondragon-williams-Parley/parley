package com.parley.parley.controllers;

import org.springframework.ui.Model;
import com.parley.parley.models.User;
import com.parley.parley.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public String viewUserProfile(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping("/{userId}/update")
    public String updateProfile(@PathVariable Long userId, @ModelAttribute User updatedUser, Model model) {
        User existingUser = userService.getUserById(userId);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setBio(updatedUser.getBio());
        existingUser.setProfile_pic(updatedUser.getProfile_pic());
        existingUser.setBanner_img(updatedUser.getBanner_img());

        User updatedUserProfile = userService.updateUserProfile(existingUser);

        model.addAttribute("user", updatedUserProfile);

        return "redirect:/profile/" + userId;
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
//
//        } catch (Exception e) {
//            String errorMessage = ("An error occurred while processing your request. Please try again later.");
//            model.addAttribute("errorMessage", errorMessage);
//            System.out.println(errorMessage);
//        }
//        return "redirect:/profile/" + userId;
//    }
}

