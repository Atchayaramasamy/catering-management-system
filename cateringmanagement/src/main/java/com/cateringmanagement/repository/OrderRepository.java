package com.cateringmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cateringmanagement.entity.Order;
import com.cateringmanagement.entity.User;

//Repository interface for Order entity
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
 // Method to find orders by user ID
 List<Order> findByUserUserId(int userId);

 // Method to find orders by user ID and not marked as deleted
 List<User> findByDeletedFalse();

 // Method to find orders by user ID and not marked as deleted
 List<Order> findByUserUserIdAndDeletedFalse(int userId);
}