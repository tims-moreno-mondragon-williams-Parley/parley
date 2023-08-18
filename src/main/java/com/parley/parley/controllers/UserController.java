package com.parley.parley.controllers;

import com.parley.parley.models.User;
import com.parley.parley.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    /* userDao is used for CRUD functionality for users */
    private final UserRepository userDao;

    /* password Encoder is used to hash passwords */
    private final PasswordEncoder passwordEncoder;

    /* Constructor for initializing the User Dao and Password Encoder */
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    /* Redirect user to the Registration Page */
    @GetMapping({"/register", "/register/"})
    public String showRegistration(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    /*
        Attempt to register new User and redirect to Login Page
        if username already exists, return to registration and
        pass an error message back.
    */
    @PostMapping({"/register", "/register/"})
    public String saveUser(@ModelAttribute User user, Model model){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.set_admin(false);
        try{
            userDao.save(user);
            return "redirect:/login";
        } catch (Exception e){
            model.addAttribute("error", "Username Already Exists. Please try again.");
            return "user/register";
        }
    }
}
