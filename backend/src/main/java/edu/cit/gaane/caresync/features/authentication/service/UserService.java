package edu.cit.gaane.caresync.features.authentication.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

import edu.cit.gaane.caresync.config.JwtService;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.authentication.repository.UserRepository;
import edu.cit.gaane.caresync.audit.service.AuditLogService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuditLogService auditLogService;

    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuditLogService auditLogService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder != null
                ? passwordEncoder
                : new BCryptPasswordEncoder();

        this.jwtService = jwtService;
        this.auditLogService = auditLogService;

    }

    public UserEntity register(UserEntity user) {

        if (user.getEmail() == null ||
        !user.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@(.+)$"
        )) {

            throw new IllegalArgumentException(
                "Please enter a valid email address."
            );
        }
        
        if (user.getRole().equalsIgnoreCase("doctor")) {
        user.setRole("Doctor");
        } 
        else if (user.getRole().equalsIgnoreCase("clinic staff")
                || user.getRole().equalsIgnoreCase("staff")) {
            user.setRole("Clinic Staff");
        }

        user.setActive(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser =
        userRepository.save(user);

        auditLogService.createLog(

                "REGISTER",

                "AUTHENTICATION",

                "Registered new account: "
                        + savedUser.getEmail(),

                savedUser.getId()

        );

        return savedUser;
    }

    public Optional<UserEntity> login(String email, String password) {
        Optional<UserEntity> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            UserEntity storedUser = user.get();
            String storedPassword = storedUser.getPassword();

            boolean matches = storedPassword != null && passwordEncoder.matches(password, storedPassword);

            if (matches) {

            if (!storedUser.getActive()) {
                return Optional.empty();
            }

            auditLogService.createLog(
                storedUser,
                "LOGIN",
                "AUTHENTICATION",
                "Logged into the system.",

                storedUser.getId()

        );

        return Optional.of(storedUser);

        }

            if (storedPassword != null && storedPassword.equals(password)) {
                storedUser.setPassword(passwordEncoder.encode(password));
                userRepository.save(storedUser);
                auditLogService.createLog(

                    "LOGIN",

                    "AUTHENTICATION",

                    "Logged into the system.",

                    storedUser.getId()

            );

            return Optional.of(storedUser);
                    }
        }

        

        return Optional.empty();
    }

    public void changePassword(UserEntity user, String newPassword) {

        user.setPassword(passwordEncoder.encode(newPassword));

        user.setMustChangePassword(false);

        userRepository.save(user);
        auditLogService.createLog(

            "CHANGE_PASSWORD",

            "AUTHENTICATION",

            "Changed account password.",

            user.getId()

    );


    }
}