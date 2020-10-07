package com.peytoncysewski.codefellowship.controllers;

import com.peytoncysewski.codefellowship.models.user.ApplicationUser;
import com.peytoncysewski.codefellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

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
        return new RedirectView("/");
    }
}
