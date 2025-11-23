package com.fooddelivery.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record MenuItemRequest(
    @NotBlank(message = "Item name is required")
    String name,

    String description,

    @Positive(message = "Price must be greater than zero")
    BigDecimal price
) {}