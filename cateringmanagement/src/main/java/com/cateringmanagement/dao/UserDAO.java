package com.cateringmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cateringmanagement.entity.Order;
import com.cateringmanagement.entity.User;
import com.cateringmanagement.repository.OrderRepository;
import com.cateringmanagement.repository.UserRepository;

import jakarta.transaction.Transactional;

//DAO class for user-related operations
@Repository
public class UserDAO {
 
 @Autowired
 private final UserRepository userRepository;
 private final OrderRepository orderRepository;
 
 public UserDAO(UserRepository userRepository, OrderRepository orderRepository) {
     this.userRepository = userRepository;
     this.orderRepository = orderRepository;
 }
 
 // Method to retrieve a user by ID
 public User getUserById(int userId) {
     return userRepository.findById(userId).orElse(null);
 }

 // Method to create a new user
 public User createUser(User user) {
     return userRepository.save(user);
 }

 // Method to update an existing user
 public User updateUser(int userId, User updatedUser) {
     User existingUser = userRepository.findById(userId).orElse(null);
     if (existingUser != null) {
         // Update user fields
         existingUser.setUserName(updatedUser.getUserName());
         existingUser.setPassword(updatedUser.getPassword());
         existingUser.setRole(updatedUser.getRole());
         // Save updated user
         return userRepository.save(existingUser);
     }
     return null;
 }

 // Method to perform soft deletion of a user
 @Transactional
 public void softDeleteUser(int userId) {
     Optional<User> optionalUser = userRepository.findById(userId);
     if (optionalUser.isPresent()) {
         User user = optionalUser.get();
         user.setDeleted(true);
         userRepository.save(user);
     }
 }

 // Method to find a user by username
 public User findByUserName(String userName) {
     return userRepository.findByUserName(userName);
 }

 // Method to find a user by username and password (for login)
 public User findByUserNameAndPassword(String userName, String password) {
     return userRepository.findByUserNameAndPassword(userName, password);
 }
}
