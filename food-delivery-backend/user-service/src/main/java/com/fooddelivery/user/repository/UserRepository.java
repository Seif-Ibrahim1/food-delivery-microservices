package com.fooddelivery.user.repository;

import com.fooddelivery.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository gives us methods like save(), findById(), findAll() automatically
}