package com.cateringmanagement.repository;

import com.cateringmanagement.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository interface for OrderDetails entity
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
 // Method to find order details by order ID
 List<OrderDetails> findByOrderOrderId(int orderId);
}