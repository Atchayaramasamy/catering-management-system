package com.cateringmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cateringmanagement.dao.OrderDAO;
import com.cateringmanagement.entity.Order;
import com.cateringmanagement.repository.OrderDetailsRepository;

import java.time.LocalDateTime;
import java.util.List;

//Service class for order-related operations
@Service
public class OrderService {
	@Autowired
	private final OrderDAO orderDao;
 private final OrderDetailsRepository orderDetailRepository; // Add OrderDetailRepository

 // Constructor injection of OrderDAO and OrderDetailsRepository
 public OrderService(OrderDAO orderDao, OrderDetailsRepository orderDetailRepository) {
     this.orderDao = orderDao;
     this.orderDetailRepository = orderDetailRepository; // Initialize OrderDetailRepository
 }

 // Method to retrieve all orders
 public List<Order> getAllOrders() {
     return orderDao.getAllOrders();
 }

 // Method to retrieve an order by its ID
 public Order getOrderById(int orderId) {
     return orderDao.getOrderById(orderId);
 }

 // Method to create a new order
 public Order createOrder(Order order) {
     order.updateTotalAmount();
     order.setModifiedAt(LocalDateTime.now());
     order.setModifiedBy("admin"); // Or get the current user dynamically
     return orderDao.createOrder(order);
 }

 // Method to update an existing order
 public Order updateOrder(int orderId, Order order) {
     Order existingOrder = orderDao.getOrderById(orderId);
     if (existingOrder != null) {
         existingOrder.setUser(order.getUser());
         existingOrder.setDeliveryLocation(order.getDeliveryLocation());
         existingOrder.setOrderStatus(order.getOrderStatus());
         existingOrder.setTotalAmount(order.getTotalAmount());
         existingOrder.setModifiedAt(LocalDateTime.now());
         existingOrder.setModifiedBy("admin"); // Or get the current user dynamically

         existingOrder.updateTotalAmount();
         return orderDao.updateOrder(orderId, existingOrder);
     }
     return null;
 }

 // Method to delete an order
 public void deleteOrder(int orderId) {
     orderDao.deleteOrder(orderId);
 }

}