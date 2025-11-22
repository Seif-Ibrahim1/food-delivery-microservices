package com.fooddelivery.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String active; // e.g. "OPEN", "CLOSED"

    // One Restaurant has many Menu Items
    // "mappedBy" refers to the field name in the MenuItem class
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;
}