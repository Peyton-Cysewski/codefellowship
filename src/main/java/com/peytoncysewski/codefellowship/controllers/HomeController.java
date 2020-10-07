package com.peytoncysewski.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String renderHome() {
        System.out.println("Rendering the Home page");
        return "home";
    }

    @GetMapping("/signup")
    public String renderSignup() {
        System.out.println("Rendering the Signup page");
        return "signup";
    }
}
