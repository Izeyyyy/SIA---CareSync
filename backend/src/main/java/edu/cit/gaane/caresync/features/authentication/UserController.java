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

    return userService.login(
            request.getEmail(),
            request.getPassword()
    )
    .map(user -> ResponseEntity.ok(
            new LoginResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getMiddleInitial(),
                    user.getEmail(),
                    user.getRole()
            )
    ))
    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

}
}