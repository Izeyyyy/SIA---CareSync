package edu.cit.gaane.caresync.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.entities.ProfileEntity;
import edu.cit.gaane.caresync.repositories.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder != null ? passwordEncoder : new BCryptPasswordEncoder();
    }

    // REGISTER
    public ProfileEntity register(ProfileEntity profile) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profileRepository.save(profile);
    }

    // LOGIN
    public Optional<ProfileEntity> login(String email, String password) {
        Optional<ProfileEntity> user = profileRepository.findByEmail(email);

        if (user.isPresent()) {
            ProfileEntity storedUser = user.get();
            String storedPassword = storedUser.getPassword();

            boolean matches = storedPassword != null && passwordEncoder.matches(password, storedPassword);

            if (matches) {
                return Optional.of(storedUser);
            }

            if (storedPassword != null && storedPassword.equals(password)) {
                storedUser.setPassword(passwordEncoder.encode(password));
                profileRepository.save(storedUser);
                return Optional.of(storedUser);
            }
        }

        return Optional.empty();
    }
}