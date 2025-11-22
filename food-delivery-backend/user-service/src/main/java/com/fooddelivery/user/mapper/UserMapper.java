package com.fooddelivery.user.mapper;

import com.fooddelivery.user.dto.UserRequest;
import com.fooddelivery.user.dto.UserResponse;
import com.fooddelivery.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .address(request.address())
                .role(request.role())
                .build();
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress(),
                user.getRole()
        );
    }
}