package edu.cit.gaane.caresync.features.userManagement.service;

import edu.cit.gaane.caresync.features.authentication.UserEntity;
import edu.cit.gaane.caresync.features.authentication.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {

    private final UserRepository userRepository;

    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}