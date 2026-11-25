package com.example.ai_mock_interview_backend.controller;
import com.example.ai_mock_interview_backend.dto.RegisterRequest;
import com.example.ai_mock_interview_backend.dto.UserResponse;
import com.example.ai_mock_interview_backend.entity.User;
import com.example.ai_mock_interview_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController{
    @Autowired
    private UserService userService;

    // Register user
    @PostMapping("/register")
    public UserResponse registerUser(
            @Valid @RequestBody RegisterRequest request) {

        return userService.registerUser(request);
    }
    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}