package com.party.eventmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.eventmanagement.dto.request.RegisterRequest;
import com.party.eventmanagement.dto.request.UserUpdateRequest;
import com.party.eventmanagement.dto.response.MessageResponse;
import com.party.eventmanagement.enums.Role;
import com.party.eventmanagement.model.User;
import com.party.eventmanagement.repository.UserRepo;
import com.party.eventmanagement.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Tag(name = "Admin Controls", description = "Endpoints for admin")
public class AdminController {
    private UserService userService;
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/Addusers")
    @PreAuthorize("hasRole('Admin')")
    @Operation(summary = "Add user", description = "Allows admin to create user.")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
	    if (userRepository.existsByName(registerRequest.getName())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(registerRequest.getEmail())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	    }

		User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
		user.setRole(Role.User);
        // Save the user entity to the database
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User created successfully!"));
    }


    @PutMapping("/users/{userId}")
	@PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> AdminupdateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest) {
	    User existingUser = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("Error: User not found."));

	    existingUser.setName(userUpdateRequest.getName());
	    existingUser.setEmail(userUpdateRequest.getEmail());

	    if (userUpdateRequest.getPassword() != null && !userUpdateRequest.getPassword().isEmpty()) {
	        existingUser.setPassword(encoder.encode(userUpdateRequest.getPassword()));
	    }
        userRepository.save(existingUser);
        return ResponseEntity.ok(new MessageResponse("User updated successfully."));
    }


    @DeleteMapping("/users/{userId}")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
	    userService.deleteUser(userId);
	    return ResponseEntity.ok(new MessageResponse("User deleted successfully."));
	}

   
}