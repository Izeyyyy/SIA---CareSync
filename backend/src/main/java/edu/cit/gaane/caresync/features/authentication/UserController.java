package edu.cit.gaane.caresync.features.authentication;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.features.authentication.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    LoginResponse response = new LoginResponse(
            loggedInUser.getId(),
            loggedInUser.getFirstName(),
            loggedInUser.getLastName(),
            loggedInUser.getMiddleInitial(),
            loggedInUser.getEmail(),
            loggedInUser.getRole()
    );

    return ResponseEntity.ok(response);
}

}