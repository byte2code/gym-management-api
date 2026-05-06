package com.CN.Gym.controller;

import com.CN.Gym.dto.GymDto;
import com.CN.Gym.model.Gym;
import com.CN.Gym.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    // 1. ADMIN - Get all gyms
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Gym> getAllGyms() {
	return gymService.getAllGyms();
    }

    // 2. ADMIN - Get gym by ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Gym getGymById(@PathVariable Long id) {
	return gymService.getGymById(id);
    }

    // 3. ADMIN - Create new gym
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Gym createGym(@RequestBody GymDto gymDto) {
	return gymService.createGym(gymDto);
    }

    // 4. ADMIN - Update gym by ID
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Gym updateGym(@PathVariable Long id, @RequestBody GymDto gymDto) {
	return gymService.updateGym(gymDto,id);
    }

    // 5. ADMIN - Delete gym by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteGym(@PathVariable Long id) {
	gymService.deleteGymById(id);
    }

    // 6. ADMIN - Add member to a gym
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMember")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(@RequestParam Long userId, @RequestParam Long gymId) {
	gymService.addMember(userId, gymId);
    }

    // 7. ADMIN - Remove member from a gym
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteMember")
    public void deleteMember(@RequestParam Long userId, @RequestParam Long gymId) {
	gymService.deleteMember(userId, gymId);
    }
}
