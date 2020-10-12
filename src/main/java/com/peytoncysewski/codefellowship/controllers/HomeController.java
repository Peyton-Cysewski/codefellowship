package com.peytoncysewski.codefellowship.controllers;

import com.peytoncysewski.codefellowship.models.user.ApplicationUser;
import com.peytoncysewski.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String renderHome(Principal p, Model m) {
        if (p == null) {
            System.out.println("null");
        } else {
            System.out.println(p.getName());
            ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("user", user);
        }
        System.out.println("Rendering the Home page");
        return "home";
    }

    @GetMapping("/signup")
    public String renderSignup() {
        System.out.println("Rendering the Signup page");
        return "signup";
    }

    @GetMapping("/login")
    public String renderLogin() {
        System.out.println("Rendering the Login page");
        return "login";
    }
}
