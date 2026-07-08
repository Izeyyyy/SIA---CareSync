package edu.cit.gaane.caresync.features.authentication;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder != null ? passwordEncoder : new BCryptPasswordEncoder();
    }

    public UserEntity register(UserEntity user) {
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