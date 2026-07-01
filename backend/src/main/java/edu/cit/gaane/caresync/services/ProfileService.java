package edu.cit.gaane.caresync.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.cit.gaane.caresync.entities.ProfileEntity;
import edu.cit.gaane.caresync.repositories.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // REGISTER
    public ProfileEntity register(ProfileEntity profile) {
        return profileRepository.save(profile);
    }

    // LOGIN (simple version for now)
    public Optional<ProfileEntity> login(String email, String password) {
        Optional<ProfileEntity> user = profileRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }

        return Optional.empty();
    }
}