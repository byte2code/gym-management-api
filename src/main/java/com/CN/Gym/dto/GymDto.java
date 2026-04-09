package com.CN.Gym.dto;

import com.CN.Gym.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Lombok generates getters, setters, constructors, toString, equals, and hashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymDto {

    private String name;
    private String address;
    private Long contactNo;
    private String membershipPlans;
    private String facilities;

    // Initialize members list to avoid NullPointerExceptions
    private List<User> members = new ArrayList<>();
}
