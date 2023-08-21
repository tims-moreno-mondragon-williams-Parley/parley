package com.parley.parley.controllers;

import com.parley.parley.models.User;
import com.parley.parley.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@Controller
public class UserController {
    /* userDao is used for CRUD functionality for users */
    private final UserRepository userDao;

    /* password Encoder is used to hash passwords */
    private final PasswordEncoder passwordEncoder;

//    private static final Pattern passwordQualifications = Pattern.compile("^(?=, *[A-Z])(?=,*\\d), {8,}$");

    /* Constructor for initializing the User Dao and Password Encoder */
    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    /* Redirect user to the Registration Page */
    @GetMapping({"/register", "/register/"})
    public String showRegistration(Model model){
        model.addAttribute("user", new User());
        return "users/registration-page";
    }

    /*
        Attempt to register new User and redirect to Login Page
        if username already exists, return to registration and
        pass an error message back.
    */
    @PostMapping({"/register", "/register/"})
    public String saveUser(
            @ModelAttribute User user,
//            @RequestParam(name = "confirmPassword") String confirmPassword,
//            BindingResult bindingResult,
            Model model
    ){
//        if (bindingResult.hasErrors()){
//            return  "users/registration-page";
//        }
////
//        if (!user.getPassword().equals(confirmPassword)){
//            bindingResult.rejectValue("password", "password.mismatch", "Passwords do not match.");
//            return "users/registration-page";
//        }

//        if (!passwordQualifications.matcher(user.getPassword()).matches()){
//           bindingResult.rejectValue("password", "password.invalid", """
//                   Password must meet the following requirements:
//                    - Between 8 and 50 characters long.
//                    - Contain at least one Uppercase letter.
//                    - Contain at least one lowercase letter.
//                    - Contain at least one digit.
//                   """);
//           return "users/registration-page";
//        }


        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.set_admin(false);
        try{
            userDao.save(user);
            return "redirect:/login";
        } catch (Exception e){
            model.addAttribute("error", "Username Already Exists. Please try again.");
            return "users/registration-page";
        }
    }
}
