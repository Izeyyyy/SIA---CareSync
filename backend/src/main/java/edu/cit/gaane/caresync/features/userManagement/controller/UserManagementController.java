package edu.cit.gaane.caresync.features.userManagement.controller;

import edu.cit.gaane.caresync.features.authentication.UserEntity;
import edu.cit.gaane.caresync.features.userManagement.dto.UserResponse;
import edu.cit.gaane.caresync.features.userManagement.service.UserManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
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