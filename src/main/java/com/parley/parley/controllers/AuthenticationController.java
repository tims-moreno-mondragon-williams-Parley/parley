package com.parley.parley.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Both fields are required!");
            return "login/Login";
        }
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.chars().anyMatch(Character::isDigit);

        if (!hasUppercase || !hasLowercase || !hasNumber) {
            model.addAttribute("error", "Password must contain at least one uppercase letter, one number, and one lowercase letter.");
            return "user/Login";
        }
        return "redirect:/somePath";
    }
}

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}

