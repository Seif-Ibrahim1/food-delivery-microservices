package com.fooddelivery.order.client;

import com.fooddelivery.order.dto.RestaurantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service")
public interface RestaurantClient {

    @GetMapping("/restaurants/{id}")
    RestaurantDTO getRestaurantById(@PathVariable("id") Long id);
}