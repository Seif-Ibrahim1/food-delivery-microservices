package com.fooddelivery.order.service;

import com.fooddelivery.order.client.RestaurantClient;
import com.fooddelivery.order.client.UserClient;
import com.fooddelivery.order.dto.RestaurantDTO;
import com.fooddelivery.order.dto.UserDTO;
import com.fooddelivery.order.entity.Order;
import com.fooddelivery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fooddelivery.order.event.OrderPlacedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;             // Feign Client
    private final RestaurantClient restaurantClient; // Feign Client
    private final RabbitTemplate rabbitTemplate; // RabbitMQ Client

    public Order createOrder(Order order) {
        // We do NOT need to check "if (user == null)".
        // If the user doesn't exist, userClient throws a FeignException immediately.
        // The GlobalExceptionHandler will catch that and return a 404.
        
        // 1. Verify User exists (Will throw exception if 404)
        UserDTO user = userClient.getUserById(order.getUserId());

        // 2. Verify Restaurant exists (Will throw exception if 404)
        RestaurantDTO restaurant = restaurantClient.getRestaurantById(order.getRestaurantId());

        // 3. Set default values
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("CREATED");
        
        // 4. Save
        Order savedOrder = orderRepository.save(order);

        // --- ASYNC EVENT ---
        // Create the event object
        OrderPlacedEvent event = new OrderPlacedEvent(savedOrder.getId(), user.email());
        
        // Send it to RabbitMQ
        // exchangeName, routingKey, object
        rabbitTemplate.convertAndSend("order.exchange", "order.placed", event);
        System.out.println("Message Sent: " + event);

        return savedOrder;
    }
}