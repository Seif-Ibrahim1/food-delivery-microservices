package com.fooddelivery.restaurant.controller;

import com.fooddelivery.restaurant.dto.*;
import com.fooddelivery.restaurant.entity.MenuItem;
import com.fooddelivery.restaurant.entity.Restaurant;
import com.fooddelivery.restaurant.mapper.RestaurantMapper;
import com.fooddelivery.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    // Create a Restaurant
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantResponse createRestaurant(@RequestBody @Valid RestaurantRequest request) {
        Restaurant restaurant = restaurantMapper.toEntity(request);
        Restaurant saved = restaurantService.createRestaurant(restaurant);
        return restaurantMapper.toResponse(saved);
    }

    // Get All Restaurants
    @GetMapping
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantService.getAllRestaurants().stream()
                .map(restaurantMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    // Get One Restaurant
    @GetMapping("/{id}")
    public RestaurantResponse getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return restaurantMapper.toResponse(restaurant);
    }

    // Add Menu Item to a Restaurant
    @PostMapping("/{id}/menu-items")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemResponse addMenuItem(@PathVariable Long id, @RequestBody @Valid MenuItemRequest request) {
        MenuItem menuItem = restaurantMapper.toEntity(request);
        MenuItem savedItem = restaurantService.addMenuItem(id, menuItem);
        return restaurantMapper.toResponse(savedItem);
    }
    
    // Get Menu for a Restaurant
    @GetMapping("/{id}/menu-items")
    public List<MenuItemResponse> getMenuByRestaurant(@PathVariable Long id) {
        return restaurantService.getMenuByRestaurant(id).stream()
                .map(restaurantMapper::toResponse)
                .collect(Collectors.toList());
    }
}