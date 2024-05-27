package com.cateringmanagement.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cateringmanagement.entity.Order;
import com.cateringmanagement.repository.OrderRepository;

//DAO class for order-related operations
@Repository
public class OrderDAO {
 
 private final OrderRepository orderRepository;

 @Autowired
 public OrderDAO(OrderRepository orderRepository) {
     this.orderRepository = orderRepository;
 }

 // Method to retrieve all orders
 public List<Order> getAllOrders() {
     return orderRepository.findAll();
 }

 // Method to retrieve an order by ID
 public Order getOrderById(int orderId) {
     return orderRepository.findById(orderId).orElse(null);
 }

 // Method to create a new order
 public Order createOrder(Order order) {
     order.updateTotalAmount();
     return orderRepository.save(order);
 }

 // Method to update an existing order
 public Order updateOrder(int orderId, Order order) {
     Order existingOrder = orderRepository.findById(orderId).orElse(null);
     if (existingOrder != null) {
         existingOrder.setUser(order.getUser());
         existingOrder.setDeliveryLocation(order.getDeliveryLocation());
         existingOrder.setOrderStatus(order.getOrderStatus());
         existingOrder.setTotalAmount(order.getTotalAmount());
         existingOrder.setModifiedAt(order.getModifiedAt());
      // Set the user who modified the order
         existingOrder.setModifiedBy(order.getModifiedBy());
         
         // Update the total amount of the order
         existingOrder.updateTotalAmount();
         
         // Save the updated order
         return orderRepository.save(existingOrder);
     }
     return null;
 }

 // Method to delete an order
 public void deleteOrder(int orderId) {
     orderRepository.deleteById(orderId);
 }
}