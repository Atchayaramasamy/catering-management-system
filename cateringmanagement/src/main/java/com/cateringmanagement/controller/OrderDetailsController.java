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

import com.cateringmanagement.entity.OrderDetails;
import com.cateringmanagement.service.OrderDetailsService;

//Controller class for handling order details-related endpoints
@RestController
@RequestMapping("/orderdetails")
public class OrderDetailsController {
 // Injecting OrderDetailsService to handle order details-related operations
 @Autowired
 private OrderDetailsService orderDetailService;

 // Endpoint to display all order details
 @GetMapping("/displayAll")
 public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
     List<OrderDetails> allOrderDetails = orderDetailService.getAllOrderDetails();
     return ResponseEntity.ok(allOrderDetails);
 }

 // Endpoint to get an order detail by its ID
 @GetMapping("/getById/{orderDetailId}")
 public ResponseEntity<OrderDetails> getOrderDetailById(@PathVariable int orderDetailId) {
     OrderDetails orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
     if (orderDetail != null) {
         return ResponseEntity.ok(orderDetail);
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 
 // Endpoint to create a new order detail
 @PostMapping("/create")
 public ResponseEntity<OrderDetails> createOrderDetail(@RequestBody OrderDetails orderDetail) {
     OrderDetails createdOrderDetail = orderDetailService.createOrderDetail(orderDetail);
     return ResponseEntity.ok(createdOrderDetail);
 }
 
 // Endpoint to update an existing order detail
 @PutMapping("/update/{orderDetailId}")
 public ResponseEntity<OrderDetails> updateOrderDetail(@PathVariable int orderDetailId, @RequestBody OrderDetails orderDetail) {
     OrderDetails updatedOrderDetail = orderDetailService.updateOrderDetail(orderDetailId, orderDetail);
     if (updatedOrderDetail != null) {
         return ResponseEntity.ok(updatedOrderDetail);
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 
 // Endpoint to delete an order detail
 @DeleteMapping("/delete/{orderDetailId}")
 public ResponseEntity<Void> deleteOrderDetail(@PathVariable int orderDetailId) {
     orderDetailService.deleteOrderDetail(orderDetailId);
     return ResponseEntity.noContent().build();
 }
}
