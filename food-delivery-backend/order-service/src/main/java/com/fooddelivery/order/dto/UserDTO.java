package com.fooddelivery.order.dto;

// Changed from Class to Record for consistency
public record UserDTO(
    Long id,
    String name,
    String email,
    String address
) {}