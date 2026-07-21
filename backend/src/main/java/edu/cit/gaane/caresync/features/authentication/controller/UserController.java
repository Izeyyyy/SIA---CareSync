package edu.cit.gaane.caresync.features.authentication.controller;

import java.util.Map;
import org.springframework.security.core.Authentication;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.config.JwtService;
import edu.cit.gaane.caresync.features.authentication.dto.ChangePasswordRequest;
import edu.cit.gaane.caresync.features.authentication.dto.LoginResponse;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.authentication.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        try {
            UserEntity savedUser = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "An account with this email already exists."));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Registration failed. Please check your details and try again."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity request) {

        var user = userService.login(
                request.getEmail(),
                request.getPassword()
        );

        if (user.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message",
                            "Invalid email or password"
                    ));
    }

    UserEntity loggedInUser = user.get();

    String token = jwtService.generateToken(
            loggedInUser.getEmail(),
            loggedInUser.getRole()
    );

    LoginResponse response = new LoginResponse(
            token,
            loggedInUser.getId(),
            loggedInUser.getFirstName(),
            loggedInUser.getLastName(),
            loggedInUser.getMiddleInitial(),
            loggedInUser.getEmail(),
            loggedInUser.getRole(),
            loggedInUser.getMustChangePassword()
    );

    return ResponseEntity.ok(response);
}

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {

        UserEntity user = (UserEntity) authentication.getPrincipal();

        userService.changePassword(
                user,
                request.getNewPassword()
        );

        return ResponseEntity.ok("Password updated successfully.");
    }
        

}