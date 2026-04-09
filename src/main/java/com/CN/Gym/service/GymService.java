package com.CN.Gym.service;

import com.CN.Gym.dto.GymDto;
import com.CN.Gym.exception.GymNotFoundException;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Gym;
import com.CN.Gym.model.User;
import com.CN.Gym.repository.GymRepository;
import com.CN.Gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Get all gyms
    public List<Gym> getAllGyms() {
	return gymRepository.findAll();
    }

    // 2. Get gym by ID
    public Gym getGymById(Long id) {
	return gymRepository.findById(id).orElseThrow(() -> new GymNotFoundException("Gym not found with ID: " + id));
    }

    // 3. Delete gym by ID
    public void deleteGymById(Long id) {
	Gym gym = getGymById(id);
	gymRepository.delete(gym);
    }

    // 4. Update gym
    public Gym updateGym(GymDto gymDto, Long id) {
	Gym gym = getGymById(id);
	gym.setName(gymDto.getName());
	gym.setAddress(gymDto.getAddress());
	gym.setContactNo(gymDto.getContactNo());
	gym.setMembershipPlans(gymDto.getMembershipPlans());
	gym.setFacilities(gymDto.getFacilities());
	return gymRepository.save(gym);
    }

    // 5. Create new gym
    public Gym createGym(GymDto gymDto) {
	Gym gym = new Gym();
	gym.setName(gymDto.getName());
	gym.setAddress(gymDto.getAddress());
	gym.setContactNo(gymDto.getContactNo());
	gym.setMembershipPlans(gymDto.getMembershipPlans());
	gym.setFacilities(gymDto.getFacilities());
	return gymRepository.save(gym);
    }



    @Transactional
    public void addMember(Long userId, Long gymId) {
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new GymNotFoundException("Gym not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setGym(gym);
        gym.getMembers().add(user);
        userRepository.save(user);

        // Force initialization so tests can inspect members after method returns
        Hibernate.initialize(gym.getMembers());
    }




    @Transactional
    public void deleteMember(Long userId, Long gymId) {
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new GymNotFoundException("Gym not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        gym.getMembers().remove(user);
        user.setGym(null);
        userRepository.save(user);

        Hibernate.initialize(gym.getMembers());
    }




}
