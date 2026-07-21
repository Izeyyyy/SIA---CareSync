package edu.cit.gaane.caresync.features.userManagement.service;

import edu.cit.gaane.caresync.audit.service.AuditLogService;
import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.authentication.repository.UserRepository;
import edu.cit.gaane.caresync.audit.service.AuditLogService;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditLogService auditLogService;

    public UserManagementService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuditLogService auditLogService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.auditLogService = auditLogService;
    }

    public List<UserEntity> getUsers(String search) {

        System.out.println("Search = " + search);

        if (search == null || search.isBlank()) {
            return userRepository.findAll();
        }

        List<UserEntity> results =
                userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        search,
                        search,
                        search
                );
                System.out.println("Matches = " + results.size());

                return results;
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


    UserEntity savedUser = userRepository.save(existingUser);

        auditLogService.createLog(

                "UPDATE",

                "USER",

                "Updated account: " + savedUser.getEmail(),

                savedUser.getId()

        );

        return savedUser;

}

    public UserEntity updateUserStatus(Long id, boolean active){

        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() ->
                    new RuntimeException("User not found")
                );


        user.setActive(active);


        UserEntity savedUser = userRepository.save(user);

        auditLogService.createLog(

                savedUser.getActive()
                        ? "ACTIVATE"
                        : "DEACTIVATE",

                "USER",

                (savedUser.getActive()
                        ? "Activated "
                        : "Deactivated ")
                        + savedUser.getEmail(),

                savedUser.getId()

        );

        return savedUser;

    }

    public String resetPassword(Long id) {

    UserEntity user = userRepository
            .findById(id)
            .orElseThrow(() ->
                    new RuntimeException("User not found")
            );

    String temporaryPassword = "123456";

    user.setPassword(
            passwordEncoder.encode(temporaryPassword)
    );

    user.setMustChangePassword(true);

    userRepository.save(user);
    auditLogService.createLog(

        "RESET_PASSWORD",

        "USER",

        "Reset password for " + user.getEmail(),

        user.getId()

);

    return temporaryPassword;
}

    

    
}

