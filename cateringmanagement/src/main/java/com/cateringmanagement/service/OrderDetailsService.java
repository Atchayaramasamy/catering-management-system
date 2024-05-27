package com.cateringmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cateringmanagement.dao.OrderDetailsDAO;
import com.cateringmanagement.entity.OrderDetails;

import java.util.List;

//Service class for order details-related operations
@Service
public class OrderDetailsService {

 private final OrderDetailsDAO orderDetailsDao;

 // Constructor injection of OrderDetailsDAO
 @Autowired
 public OrderDetailsService(OrderDetailsDAO orderDetailsDao) {
     this.orderDetailsDao = orderDetailsDao;
 }

 // Method to retrieve all order details
 public List<OrderDetails> getAllOrderDetails() {
     return orderDetailsDao.getAllOrderDetails();
 }

 // Method to create a new order detail
 public OrderDetails createOrderDetail(OrderDetails orderDetail) {
     return orderDetailsDao.createOrderDetails(orderDetail);
 }

 // Method to retrieve an order detail by its ID
 public OrderDetails getOrderDetailById(int orderDetailId) {
     return orderDetailsDao.getOrderDetailsById(orderDetailId);
 }

 // Method to update an existing order detail
 public OrderDetails updateOrderDetail(int orderDetailId, OrderDetails updatedOrderDetail) {
     return orderDetailsDao.updateOrderDetails(orderDetailId, updatedOrderDetail);
 }

 // Method to delete an order detail
 public void deleteOrderDetail(int orderDetailId) {
     orderDetailsDao.deleteOrderDetails(orderDetailId);
 }
}