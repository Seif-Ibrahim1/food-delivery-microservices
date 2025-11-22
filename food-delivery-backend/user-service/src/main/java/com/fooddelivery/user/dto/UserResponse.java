package com.fooddelivery.user.dto;

public record UserResponse(
    Long id,
    String name,
    String email,
    String address,
    String role
) {}