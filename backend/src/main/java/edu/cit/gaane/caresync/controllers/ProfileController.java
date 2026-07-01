package edu.cit.gaane.caresync.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.entities.ProfileEntity;
import edu.cit.gaane.caresync.services.ProfileService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/register")
    public ProfileEntity register(@RequestBody ProfileEntity profile) {
        return profileService.register(profile);
    }

    @PostMapping("/login")
    public ProfileEntity login(@RequestBody ProfileEntity request) {
        return profileService.login(request.getEmail(), request.getPassword())
                .orElse(null);
    }
}