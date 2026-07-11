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

    public UserEntity updateUser(Long id, UserEntity updatedUser){

    UserEntity existingUser = userRepository
            .findById(id)
            .orElseThrow(() ->
                new RuntimeException("User not found")
            );


    existingUser.setFirstName(
            updatedUser.getFirstName()
    );


    existingUser.setLastName(
            updatedUser.getLastName()
    );


    existingUser.setEmail(
            updatedUser.getEmail()
    );


    existingUser.setRole(
            updatedUser.getRole()
    );


    return userRepository.save(existingUser);

}

    public UserEntity updateUserStatus(Long id, boolean active){

        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() ->
                    new RuntimeException("User not found")
                );


        user.setActive(active);


        return userRepository.save(user);

    }

    

    
}

