package com.cateringmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cateringmanagement.entity.Order;
import com.cateringmanagement.service.OrderService;


//Controller class for handling order-related endpoints
@RestController
@RequestMapping("/orders")
public class OrderController {
 // Injecting OrderService to handle order-related operations
 @Autowired
 private OrderService orderService;

 // Endpoint to display all orders
 @GetMapping("/displayAll")
 public ResponseEntity<List<Order>> getAllOrders() {
     List<Order> orders = orderService.getAllOrders();
     return ResponseEntity.ok(orders);
 }
 
 // Endpoint to get an order by its ID
 @GetMapping("/{orderId}")
 public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
     Order order = orderService.getOrderById(orderId);
     if (order != null) {
         return ResponseEntity.ok(order);
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 
 // Endpoint to create a new order
 @PostMapping("/create")
 public ResponseEntity<Order> createOrder(@RequestBody Order order) {
     Order createdOrder = orderService.createOrder(order);
     return ResponseEntity.ok(createdOrder);
 }
 
 // Endpoint to update an existing order
 @PutMapping("/update/{orderId}")
 public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody Order order) {
     Order updatedOrder = orderService.updateOrder(orderId, order);
     if (updatedOrder != null) {
         return ResponseEntity.ok(updatedOrder);
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 
 // Endpoint to delete an order
 @DeleteMapping("/delete/{orderId}")
 public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
     orderService.deleteOrder(orderId);
     return ResponseEntity.noContent().build();
 }
}