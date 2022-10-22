package com.cdac.project.bbms.repositories.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bbms/test")
public class TestController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole ('DONOR') or hasRole('ADMIN')")
    public String userAcess(){
        return "User Content.";
    }

    @GetMapping("/donor")
    @PreAuthorize("hasRole('DONOR')")
    public String donorAccess(){
        return "Donor Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "Admin Board.";
    }
}
