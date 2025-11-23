package com.fooddelivery.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
    Long orderId,
    Long userId,
    Long restaurantId,
    BigDecimal totalAmount,
    String status,
    LocalDateTime orderTime
) {}