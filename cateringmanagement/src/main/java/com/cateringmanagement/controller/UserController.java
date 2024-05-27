package com.cateringmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cateringmanagement.entity.User;
import com.cateringmanagement.service.UserService;

//Marks the class as a Spring MVC controller and specifies the base URL for all endpoints in this controller
@RestController
@RequestMapping("/users")
public class UserController {

 // Automatically injects an instance of UserService into this controller
 @Autowired
 private UserService userService;

 // Endpoint for registering a new user
 @PostMapping("/register")
 public ResponseEntity<User> registerUser(@RequestBody User user) {
     // Calls the service method to register a new user
     User registeredUser = userService.registerUser(user);
     // Returns the registered user as the response body with HTTP status 200 OK
     return ResponseEntity.ok(registeredUser);
 }

 // Endpoint for user login
 @PostMapping("/login")
 public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
     // Calls the service method to authenticate the user
     User user = userService.loginUser(loginUser.getUserName(), loginUser.getPassword());
     if (user != null) {
         // Returns the authenticated user with HTTP status 200 OK if credentials are valid
         return ResponseEntity.ok(user);
     } else {
         // Returns an error message with HTTP status 401 Unauthorized if credentials are invalid
         return ResponseEntity.status(401).body("Invalid credentials");
     }
 }

 // Endpoint for getting a user by their ID
 @GetMapping("/getById/{userId}")
 public ResponseEntity<User> getUserById(@PathVariable int userId) {
     // Calls the service method to retrieve a user by their ID
     User user = userService.getUserById(userId);
     if (user != null) {
         // Returns the user with HTTP status 200 OK if found
         return ResponseEntity.ok(user);
     } else {
         // Returns HTTP status 404 Not Found if the user is not found
         return ResponseEntity.notFound().build();
     }
 }

 // Endpoint for getting a user by their username
 @GetMapping("/byUsername/{userName}")
 public ResponseEntity<User> getUserByUsername(@PathVariable String userName) {
     // Calls the service method to find a user by their username
     Optional<User> userOptional = Optional.of(userService.findByUserName(userName));
     if (userOptional.isPresent()) {
         // Returns the user with HTTP status 200 OK if found
         return ResponseEntity.ok(userOptional.get());
     } else {
         // Returns HTTP status 404 Not Found if the user is not found
         return ResponseEntity.notFound().build();
     }
 }

 // Endpoint for updating a user's information
 @PutMapping("/update/{userId}")
 public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
     // Calls the service method to update the user
     User updatedUser = userService.updateUser(userId, user);
     if (updatedUser != null) {
         // Returns the updated user with HTTP status 200 OK if update is successful
         return ResponseEntity.ok(updatedUser);
     } else {
         // Returns HTTP status 404 Not Found if the user to be updated is not found
         return ResponseEntity.notFound().build();
     }
 }

 // Endpoint for soft deleting a user (marking as deleted without removing from the database)
 @DeleteMapping("/soft-delete/{userId}")
 public ResponseEntity<Void> softDeleteUser(@PathVariable int userId) {
     // Calls the service method to soft delete the user
     userService.softDeleteUser(userId);
     // Returns HTTP status 204 No Content indicating that the request was successful but there is no content to return
     return ResponseEntity.noContent().build();
 }
}