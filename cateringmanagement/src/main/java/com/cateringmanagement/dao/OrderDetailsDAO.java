package com.cateringmanagement.dao;

import com.cateringmanagement.entity.OrderDetails;
import com.cateringmanagement.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//DAO class for order details-related operations
@Repository
public class OrderDetailsDAO {

 private final OrderDetailsRepository orderDetailsRepository;

 @Autowired
 public OrderDetailsDAO(OrderDetailsRepository orderDetailsRepository) {
     this.orderDetailsRepository = orderDetailsRepository;
 }

 // Method to retrieve all order details
 public List<OrderDetails> getAllOrderDetails() {
     return orderDetailsRepository.findAll();
 }

 // Method to retrieve order details by ID
 public OrderDetails getOrderDetailsById(int orderDetailId) {
     return orderDetailsRepository.findById(orderDetailId).orElse(null);
 }

 // Method to create order details
 public OrderDetails createOrderDetails(OrderDetails orderDetails) {
     return orderDetailsRepository.save(orderDetails);
 }

 // Method to update order details
 public OrderDetails updateOrderDetails(int orderDetailId, OrderDetails orderDetails) {
     OrderDetails existingOrderDetails = orderDetailsRepository.findById(orderDetailId).orElse(null);
     if (existingOrderDetails != null) {
         existingOrderDetails.setOrder(orderDetails.getOrder());
         existingOrderDetails.setMenu(orderDetails.getMenu());
         existingOrderDetails.setPrice(orderDetails.getPrice());
         existingOrderDetails.setQuantity(orderDetails.getQuantity());
         return orderDetailsRepository.save(existingOrderDetails);
     }
     return null;
 }

 // Method to delete order details
 public void deleteOrderDetails(int orderDetailId) {
     orderDetailsRepository.deleteById(orderDetailId);
 }

 // Method to retrieve all order details by order ID
 public List<OrderDetails> getAllOrderDetailsByOrderId(int orderId) {
     return orderDetailsRepository.findByOrderOrderId(orderId);
 }
}