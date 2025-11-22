package com.fooddelivery.restaurant.controller;

import com.fooddelivery.restaurant.entity.MenuItem;
import com.fooddelivery.restaurant.entity.Restaurant;
import com.fooddelivery.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Create a Restaurant
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    // Get All Restaurants
    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
    
    // Get One Restaurant
    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    // Add Menu Item to a Restaurant
    @PostMapping("/{id}/menu-items")
    public MenuItem addMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return restaurantService.addMenuItem(id, menuItem);
    }
    
    // Get Menu for a Restaurant
    @GetMapping("/{id}/menu-items")
    public List<MenuItem> getMenuByRestaurant(@PathVariable Long id) {
        return restaurantService.getMenuByRestaurant(id);
    }
}