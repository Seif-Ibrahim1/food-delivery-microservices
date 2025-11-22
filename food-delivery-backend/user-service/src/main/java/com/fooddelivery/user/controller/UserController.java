package com.fooddelivery.user.controller;

import com.fooddelivery.user.dto.UserRequest;
import com.fooddelivery.user.dto.UserResponse;
import com.fooddelivery.user.entity.User;
import com.fooddelivery.user.mapper.UserMapper;
import com.fooddelivery.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 Created is better than 200 OK for creation
    public UserResponse createUser(@RequestBody @Valid UserRequest request) {
        // 1. Map DTO -> Entity
        User user = userMapper.toEntity(request);
        
        // 2. Call Business Logic
        User savedUser = userService.createUser(user);
        
        // 3. Map Entity -> DTO
        return userMapper.toResponse(savedUser);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return userMapper.toResponse(user);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
}