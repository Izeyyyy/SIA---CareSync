package edu.cit.gaane.caresync.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.authentication.repository.UserRepository;
import edu.cit.gaane.caresync.features.authentication.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private UserService userService;

    @Test
    void registerHashesPasswordBeforeSaving() {
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");
        user.setRole("staff");

        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserEntity saved = userService.register(user);

        assertTrue(saved.getPassword().startsWith("$2a$"));
        assertTrue(passwordEncoder.matches("plainPassword", saved.getPassword()));
    }

    @Test
    void loginAcceptsHashedPassword() {
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setPassword(passwordEncoder.encode("plainPassword"));
        user.setRole("staff");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<UserEntity> result = userService.login("test@example.com", "plainPassword");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }
}
