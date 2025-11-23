package com.fooddelivery.restaurant.mapper;

import com.fooddelivery.restaurant.dto.*;
import com.fooddelivery.restaurant.entity.MenuItem;
import com.fooddelivery.restaurant.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantMapper {

    // --- Restaurant Mappings ---

    public Restaurant toEntity(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.name());
        restaurant.setAddress(request.address());
        restaurant.setActive(request.active());
        return restaurant;
    }

    public RestaurantResponse toResponse(Restaurant restaurant) {
        // Convert the list of MenuItem entities to MenuItemResponses
        List<MenuItemResponse> menuItems = (restaurant.getMenuItems() != null) ?
                restaurant.getMenuItems().stream()
                        .map(this::toResponse)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        return new RestaurantResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getActive(),
                menuItems
        );
    }

    // --- Menu Item Mappings ---

    public MenuItem toEntity(MenuItemRequest request) {
        MenuItem item = new MenuItem();
        item.setName(request.name());
        item.setDescription(request.description());
        item.setPrice(request.price());
        return item;
    }

    public MenuItemResponse toResponse(MenuItem item) {
        return new MenuItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice()
        );
    }
}