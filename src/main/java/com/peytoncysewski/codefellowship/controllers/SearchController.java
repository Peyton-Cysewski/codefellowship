package com.peytoncysewski.codefellowship.controllers;

import com.peytoncysewski.codefellowship.models.user.ApplicationUser;
import com.peytoncysewski.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @GetMapping("/search")
    public String search(Principal p, Model m) {
        List<ApplicationUser> users = applicationUserRepository.findAll();
        if (p != null) {
            ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("user", user);
        }
        m.addAttribute("users", users);
        return "search";
    }
}
