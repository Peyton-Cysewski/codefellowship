package com.peytoncysewski.codefellowship.controllers;

import com.peytoncysewski.codefellowship.models.user.ApplicationUser;
import com.peytoncysewski.codefellowship.models.user.ApplicationUserRepository;
import com.peytoncysewski.codefellowship.models.user.Post;
import com.peytoncysewski.codefellowship.models.user.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/createuser")
    public RedirectView createUser(
            String username,
            String password,
            String firstName,
            String lastName,
            Date dateOfBirth,
            String bio ) {
        System.out.println("Creating a new user");
        password = passwordEncoder.encode(password);
        ApplicationUser user = new ApplicationUser(username, password, firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(user);

        // TODO: logging in after registration

        return new RedirectView("/");
    }

    @GetMapping("/user/{username}")
    public String renderProfile(@PathVariable String username, Principal p, Model m) {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        if (user == null)
            return "error";
        if (user.getUsername().equals(p.getName()))
            m.addAttribute("canPost", true);
        m.addAttribute("user", user);

        List<Post> posts = postRepository.findAllByApplicationUserOrderByCreatedAtDesc(user);
        m.addAttribute("posts", posts);
        return "profile";
    }

    @PostMapping("/follow")
    public RedirectView follow(String username, Principal p) {
        ApplicationUser follower = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser followee = applicationUserRepository.findByUsername(username);

        follower.whoTheUserFollows.add(followee);
        followee.whoIsFollowingTheUser.add(follower);

        applicationUserRepository.save(follower);
        applicationUserRepository.save(followee);

        return new RedirectView("/search");
    }
}
