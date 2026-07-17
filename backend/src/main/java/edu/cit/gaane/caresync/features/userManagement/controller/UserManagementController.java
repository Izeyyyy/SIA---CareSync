package edu.cit.gaane.caresync.features.userManagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cit.gaane.caresync.features.authentication.entity.UserEntity;
import edu.cit.gaane.caresync.features.userManagement.dto.UserResponse;
import edu.cit.gaane.caresync.features.userManagement.service.UserManagementService;

@RestController
@RequestMapping("/api/users")
public class UserManagementController {

    private final UserManagementService service;

    public UserManagementController(UserManagementService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponse> getUsers() {

        return service.getAllUsers()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getActive()
                ))
                .toList();
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(
            @PathVariable Long id,
            @RequestBody UserEntity updatedUser
    ){

        return service.updateUser(id, updatedUser);

    }

    @PutMapping("/{id}/status")
    public UserEntity updateUserStatus(
            @PathVariable Long id,
            @RequestParam boolean active
    ){

        return service.updateUserStatus(id, active);

    }
}