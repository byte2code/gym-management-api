package com.CN.Gym.controller;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.model.User;
import com.CN.Gym.model.Workout;
import com.CN.Gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. ADMIN - Get all users
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 2. PUBLIC - Register a new user
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest); // ✅ matches service
    }

    // 3. CUSTOMER - Get user by ID
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 4. CUSTOMER - Update user by ID
    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUser(userRequest, id); // ✅ fixed order
    }

    // 5. ADMIN - Delete user by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // 6. TRAINER - Assign workout to user
    @PreAuthorize("hasRole('TRAINER')")
    @PostMapping("/workout/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Workout addWorkout(@RequestBody WorkoutDto workoutDto, @PathVariable Long userId) {
        return userService.addWorkout(workoutDto, userId); // ✅ matches service
    }
}
