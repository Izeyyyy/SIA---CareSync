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

import edu.cit.gaane.caresync.entities.ProfileEntity;
import edu.cit.gaane.caresync.repositories.ProfileRepository;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    private ProfileService profileService;

    @Test
    void registerHashesPasswordBeforeSaving() {
        ProfileEntity profile = new ProfileEntity();
        profile.setEmail("test@example.com");
        profile.setPassword("plainPassword");
        profile.setRole("staff");

        when(profileRepository.save(any(ProfileEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProfileEntity saved = profileService.register(profile);

        assertTrue(saved.getPassword().startsWith("$2a$"));
        assertTrue(passwordEncoder.matches("plainPassword", saved.getPassword()));
    }

    @Test
    void loginAcceptsHashedPassword() {
        ProfileEntity profile = new ProfileEntity();
        profile.setEmail("test@example.com");
        profile.setPassword(passwordEncoder.encode("plainPassword"));
        profile.setRole("staff");

        when(profileRepository.findByEmail("test@example.com")).thenReturn(Optional.of(profile));

        Optional<ProfileEntity> result = profileService.login("test@example.com", "plainPassword");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }
}
