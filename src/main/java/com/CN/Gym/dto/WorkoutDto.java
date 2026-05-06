package com.CN.Gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok generates getters, setters, constructors, toString, equals, and hashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutDto {

    private String workoutName;
    private String description;
    private String difficultyLevel;
    private int duration; // in minutes
}
