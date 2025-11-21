package com.fooddelivery.user.service;

import com.fooddelivery.user.entity.User;
import com.fooddelivery.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Important for DB transactions

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional // Ensures database consistency
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        // Here is where we would eventually add logic like:
        // - Check if email already exists
        // - Hash the password
        // - Validate phone number
        return userRepository.save(user);
    }

    @Transactional(readOnly = true) // Optimization: tells DB we are only reading
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}