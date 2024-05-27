package com.cateringmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cateringmanagement.dao.UserDAO;
import com.cateringmanagement.entity.User;

//Service class for user-related operations
@Service
public class UserService {

 private final UserDAO userDao;

 // Constructor injection of UserDAO
 @Autowired
 public UserService(UserDAO userDao) {
     this.userDao = userDao;
 }

 // Method to retrieve a user by ID
 public User getUserById(int userId) {
     return userDao.getUserById(userId);
 }

 // Method to create a new user
 public User createUser(User user) {
     return userDao.createUser(user);
 }

 // Method to update an existing user
 public User updateUser(int userId, User updatedUser) {
     return userDao.updateUser(userId, updatedUser);
 }

 // Method to soft delete a user
 public void softDeleteUser(int userId) {
     userDao.softDeleteUser(userId);
 }
 
 // Method to handle user login
 public User loginUser(String userName, String password) {
     return userDao.findByUserNameAndPassword(userName, password);
 }

 // Method to find user by username
 public User findByUserName(String userName) {
     return userDao.findByUserName(userName);
 }

 // Method to handle user registration
 public User registerUser(User user) {
     return userDao.createUser(user);
 }
}