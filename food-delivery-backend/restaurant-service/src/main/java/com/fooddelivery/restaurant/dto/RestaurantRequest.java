package com.fooddelivery.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RestaurantRequest(
    @NotBlank(message = "Restaurant name is required")
    String name,

    @NotBlank(message = "Address is required")
    String address,

    @Pattern(regexp = "OPEN|CLOSED", message = "Status must be OPEN or CLOSED")
    String active
) {}