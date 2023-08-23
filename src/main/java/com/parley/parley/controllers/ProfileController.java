package com.parley.parley.controllers;

import org.springframework.ui.Model;
import com.parley.parley.models.User;
import com.parley.parley.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String viewUserProfile(@PathVariable Long userId, Model model, RedirectAttributes redirectAttributes) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!userId.equals(user.getId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Unable to access profile at this time.");
            return "redirect:/"; // Not sure where to redirect just yet
        }

        model.addAttribute("user", user);
        return "users/profile";
    }


    @PostMapping("/profile/{userId}")
    public String updateProfile(@ModelAttribute User updatedUser, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setBio(updatedUser.getBio());
        user.setProfile_pic(updatedUser.getProfile_pic());
        user.setBanner_img(updatedUser.getBanner_img());

        User updatedUserProfile = userService.updateUserProfile(user);

        model.addAttribute("user", updatedUserProfile);

        return "redirect:/update-profile/";
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

