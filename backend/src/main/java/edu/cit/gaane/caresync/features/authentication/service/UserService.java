package edu.cit.gaane.caresync.features.authentication.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.config.JwtService;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.authentication.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder != null
                ? passwordEncoder
                : new BCryptPasswordEncoder();

        this.jwtService = jwtService;

    }

    public UserEntity register(UserEntity user) {
        
        if (user.getRole().equalsIgnoreCase("doctor")) {
        user.setRole("Doctor");
        } 
        else if (user.getRole().equalsIgnoreCase("clinic staff")
                || user.getRole().equalsIgnoreCase("staff")) {
            user.setRole("Clinic Staff");
        }

        user.setActive(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<UserEntity> login(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            UserEntity storedUser = user.get();
            String storedPassword = storedUser.getPassword();

            boolean matches = storedPassword != null && passwordEncoder.matches(password, storedPassword);

            if (matches) {
                return Optional.of(storedUser);
            }

            if (storedPassword != null && storedPassword.equals(password)) {
                storedUser.setPassword(passwordEncoder.encode(password));
                userRepository.save(storedUser);
                return Optional.of(storedUser);
            }
        }

        return Optional.empty();
    }
}