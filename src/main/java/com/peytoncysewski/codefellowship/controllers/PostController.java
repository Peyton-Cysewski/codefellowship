package com.peytoncysewski.codefellowship.controllers;

import com.peytoncysewski.codefellowship.models.user.ApplicationUser;
import com.peytoncysewski.codefellowship.models.user.ApplicationUserRepository;
import com.peytoncysewski.codefellowship.models.user.Post;
import com.peytoncysewski.codefellowship.models.user.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/createpost")
    public RedirectView createPost(Principal p, String body, String username) {
        if (!username.equals(p.getName())) return new RedirectView("/error");
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        Post post = new Post(user, body);
        postRepository.save(post);
        return new RedirectView("/user/" + username);
    }
}
