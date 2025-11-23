package com.fooddelivery.order.mapper;

import com.fooddelivery.order.dto.OrderRequest;
import com.fooddelivery.order.dto.OrderResponse;
import com.fooddelivery.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequest request) {
        return Order.builder()
                .userId(request.userId())
                .restaurantId(request.restaurantId())
                .totalAmount(request.totalAmount())
                .build();
    }

    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getRestaurantId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getOrderTime()
        );
    }
}