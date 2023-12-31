package com.parley.parley.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "users/login";
    }

    @GetMapping("/aboutus")
    public String about(){
        return "posts/AboutUs";
    }
}
