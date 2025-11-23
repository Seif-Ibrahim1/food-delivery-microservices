package com.fooddelivery.restaurant.dto;

import java.math.BigDecimal;

public record MenuItemResponse(
    Long id,
    String name,
    String description,
    BigDecimal price
) {}