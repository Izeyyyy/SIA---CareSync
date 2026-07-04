package edu.cit.gaane.caresync.controllers;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> register(@RequestBody ProfileEntity profile) {
        try {
            ProfileEntity savedProfile = profileService.register(profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "An account with this email already exists."));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Registration failed. Please check your details and try again."));
        }
    }

    @PostMapping("/login")
    public ProfileEntity login(@RequestBody ProfileEntity request) {
        return profileService.login(request.getEmail(), request.getPassword())
                .orElse(null);
    }
}