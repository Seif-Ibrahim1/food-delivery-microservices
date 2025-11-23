package com.fooddelivery.notification.event;

public record OrderPlacedEvent(
    Long orderId,
    String email
) {}