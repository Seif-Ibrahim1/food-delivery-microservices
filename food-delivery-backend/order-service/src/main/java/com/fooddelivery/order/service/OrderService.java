package com.fooddelivery.order.service;

import com.fooddelivery.order.client.RestaurantClient;
import com.fooddelivery.order.client.UserClient;
import com.fooddelivery.order.dto.RestaurantDTO;
import com.fooddelivery.order.dto.UserDTO;
import com.fooddelivery.order.entity.Order;
import com.fooddelivery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;             // Feign Client
    private final RestaurantClient restaurantClient; // Feign Client

    public Order createOrder(Order order) {
        // 1. Validate User exists (Calls User Service)
        UserDTO user = userClient.getUserById(order.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 2. Validate Restaurant exists (Calls Restaurant Service)
        RestaurantDTO restaurant = restaurantClient.getRestaurantById(order.getRestaurantId());
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }
        
        // 3. Set default values
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("CREATED");
        
        // 4. Save
        return orderRepository.save(order);
    }
}