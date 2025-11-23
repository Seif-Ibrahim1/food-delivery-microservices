package com.fooddelivery.order.controller;

import com.fooddelivery.order.dto.OrderRequest;
import com.fooddelivery.order.dto.OrderResponse;
import com.fooddelivery.order.entity.Order;
import com.fooddelivery.order.mapper.OrderMapper;
import com.fooddelivery.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody @Valid OrderRequest request) {
        // 1. Convert DTO to Entity
        Order order = orderMapper.toEntity(request);
        
        // 2. Call Service (Business Logic + External Calls)
        Order savedOrder = orderService.createOrder(order);
        
        // 3. Convert Entity back to DTO
        return orderMapper.toResponse(savedOrder);
    }
}