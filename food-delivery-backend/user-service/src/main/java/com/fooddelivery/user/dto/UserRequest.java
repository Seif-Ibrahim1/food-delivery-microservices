package com.fooddelivery.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// Java 21 Record: Immutable data carrier
public record UserRequest(
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    String email,

    @NotBlank(message = "Address is required")
    String address,
    
    @Pattern(regexp = "CUSTOMER|RESTAURANT_OWNER", message = "Role must be CUSTOMER or RESTAURANT_OWNER")
    String role
) {}