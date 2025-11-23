package com.fooddelivery.order.event;

// Java Record (Best practice for immutable events)
public record OrderPlacedEvent(
    Long orderId,
    String email
) {}