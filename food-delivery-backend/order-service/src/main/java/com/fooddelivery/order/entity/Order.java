package com.fooddelivery.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders") // 'order' is a reserved keyword in SQL
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;        // We only store the ID, not the full User object
    private Long restaurantId;  // We only store the ID
    
    private BigDecimal totalAmount;
    private LocalDateTime orderTime;
    private String status;      // CREATED, PREPARING, DELIVERED
}