package com.cateringmanagement.entity;
//Import required packages 
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The User class represents a user in the catering management system. It includes
 * details such as username, password, role, and a list of orders associated with the user.
 * It also includes a flag to indicate if the user account is deleted.
 */

//Specifies that this class is an entity and is mapped to a database table
@Entity
//Specifies the name of the database table to be used for mapping
@Table(name = "users")
public class User {

 // Specifies the primary key of the entity
 @Id
 // Provides the specification of generation strategies for the values of primary keys
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 // Maps the field to the user_id column in the database table
 @Column(name = "user_id")
 private int userId;

 // Maps the field to the user_name column in the database table
 @Column(name = "user_name")
 private String userName;

 // Maps the field to the user_password column in the database table
 @Column(name = "user_password")
 private String password;

 // Maps the field to the user_role column in the database table
 @Column(name = "user_role")
 private String userRole;

 // Maps a one-to-many relationship between User and Order entities
 @OneToMany(mappedBy = "user")
 private List<Order> orders;

 // Maps the field to the is_deleted column in the database table
 @Column(name = "is_deleted")
 private boolean deleted = false;

 // Returns whether the user is marked as deleted
 public boolean isDeleted() {
     return deleted;
 }

 // Sets whether the user is marked as deleted
 public void setDeleted(boolean deleted) {
     this.deleted = deleted;
 }

 // Gets the user's ID
 public int getUserId() {
     return userId;
 }

 // Sets the user's ID
 public void setUserId(int userId) {
     this.userId = userId;
 }

 // Gets the user's name
 public String getUserName() {
     return userName;
 }

 // Sets the user's name
 public void setUserName(String userName) {
     this.userName = userName;
 }

 // Gets the user's password
 public String getPassword() {
     return password;
 }

 // Sets the user's password
 public void setPassword(String password) {
     this.password = password;
 }

 // Gets the user's role
 public String getRole() {
     return userRole;
 }

 // Sets the user's role
 public void setRole(String role) {
     this.userRole = role;
 }

 // Provides a string representation of the user object
 @Override
 public String toString() {
     return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userRole=" + userRole + "]";
 }
}