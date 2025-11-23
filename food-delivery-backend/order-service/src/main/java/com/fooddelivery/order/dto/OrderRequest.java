package com.fooddelivery.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record OrderRequest(
    @NotNull(message = "User ID is required")
    Long userId,

    @NotNull(message = "Restaurant ID is required")
    Long restaurantId,

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be greater than zero")
    BigDecimal totalAmount
) {}