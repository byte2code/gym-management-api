package com.CN.Gym.service;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Role;
import com.CN.Gym.model.User;
import com.CN.Gym.model.Workout;
import com.CN.Gym.repository.UserRepository;
import com.CN.Gym.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Changed return type to User
    public User createUser(UserRequest userRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userRequest.getPassword());

        User user = User.builder()
                .email(userRequest.getEmail())
                .age(userRequest.getAge())
                .gender(userRequest.getGender())
                .password(encodedPassword)
                .build();

        Role role = new Role();
        Set<Role> roles = new HashSet<>();

        if (userRequest.getUserType() != null) {
            if (userRequest.getUserType().equalsIgnoreCase("TRAINER")) {
                role.setRoleName("ROLE_TRAINER");
            } else if (userRequest.getUserType().equalsIgnoreCase("ADMIN")) {
                role.setRoleName("ROLE_ADMIN");
            } else {
                role.setRoleName("ROLE_CUSTOMER");
            }
        } else {
            role.setRoleName("ROLE_CUSTOMER");
        }

        roles.add(role);
        user.setRoles(roles);

        // Return saved user
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with ID: " + id));
    }

    // Changed return type to User
    public User updateUser(UserRequest userRequest, Long id) {
        User existingUser = getUserById(id);
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setAge(userRequest.getAge());
        existingUser.setGender(userRequest.getGender());

        if (userRequest.getPassword() != null && !userRequest.getPassword().isBlank()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            existingUser.setPassword(encoder.encode(userRequest.getPassword()));
        }

        // Return saved user
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    // Also updated to return Workout to match controller
    @Transactional
    public Workout addWorkout(WorkoutDto workoutDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Workout workout = new Workout();
        workout.setWorkoutName(workoutDto.getWorkoutName());
        workout.setDescription(workoutDto.getDescription());
        workout.setDifficultyLevel(workoutDto.getDifficultyLevel());
        workout.setDuration(workoutDto.getDuration());
        workout.setUser(user);

        Workout saved = workoutRepository.save(workout);

        // Force initialize so tests can see the workouts list
        Hibernate.initialize(user.getWorkouts());

        return saved;
    }



}
