package com.fooddelivery.restaurant.dto;

import java.util.List;

public record RestaurantResponse(
    Long id,
    String name,
    String address,
    String active,
    List<MenuItemResponse> menuItems // Nested DTOs, not Entities!
) {}