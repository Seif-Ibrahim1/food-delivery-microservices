package com.fooddelivery.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price; // Always use BigDecimal for money!

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore // Prevents infinite recursion when converting to JSON
    private Restaurant restaurant;
}