package com.fooddelivery.restaurant.service;

import com.fooddelivery.restaurant.entity.MenuItem;
import com.fooddelivery.restaurant.entity.Restaurant;
import com.fooddelivery.restaurant.repository.MenuItemRepository;
import com.fooddelivery.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional(readOnly = true)
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    @Transactional
    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        menuItem.setRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }
    
    @Transactional(readOnly = true)
    public List<MenuItem> getMenuByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
}