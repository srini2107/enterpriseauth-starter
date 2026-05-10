package com.enterpriseauthstarter.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public String currentUser(Authentication authentication) {

        return "Logged in user: " + authentication.getName();
    }
}
