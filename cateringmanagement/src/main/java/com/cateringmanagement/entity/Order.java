package com.cateringmanagement.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The Order class represents an order placed by a user, including the details of the order.
 * It includes information such as delivery location, order status, total amount, and modification details.
 * The class also maintains a flag to indicate if the order is deleted.
 */

//Specifies that this class is an entity and is mapped to a database table
@Entity
//Specifies the name of the database table to be used for mapping
@Table(name = "orders")
public class Order {

 // Specifies the primary key of the entity
 @Id
 // Provides the specification of generation strategies for the values of primary keys
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int orderId;

 // Specifies a many-to-one relationship between Order and User entities
 @ManyToOne
 // Specifies the join column for the relationship
 @JoinColumn(name = "user_id")
 private User user;

 // Specifies a one-to-many relationship between Order and OrderDetails entities
 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
 private Set<OrderDetails> orderDetails;

 // Maps the field to the is_deleted column in the database table
 @Column(name = "is_deleted")
 private boolean deleted = false;

 // Maps the field to a column in the database table
 @Column(nullable = false)
 private String deliveryLocation;

 // Maps the field to a column in the database table
 @Column(nullable = false)
 private String orderStatus;

 // Maps the field to a column in the database table
 @Column(nullable = false)
 private Double totalAmount;

 // Maps the field to a column in the database table
 @Column(nullable = false)
 private LocalDateTime modifiedAt;

 // Maps the field to a column in the database table
 @Column(nullable = false)
 private String modifiedBy;

 // Provides a string representation of the order object
 @Override
 public String toString() {
     return "Order [orderId=" + orderId + ", user=" + user + ", deliveryLocation=" + deliveryLocation + ", orderStatus=" + orderStatus + ", totalAmount=" + totalAmount + ", modifiedAt=" + modifiedAt + "]";
 }

 // Gets the order ID
 public int getOrderId() {
     return orderId;
 }

 // Sets the order ID
 public void setOrderId(int orderId) {
     this.orderId = orderId;
 }

 // Gets the user associated with this order
 public User getUser() {
     return user;
 }

 // Sets the user associated with this order
 public void setUser(User user) {
     this.user = user;
 }

 // Gets the delivery location for the order
 public String getDeliveryLocation() {
     return deliveryLocation;
 }

 // Sets the delivery location for the order
 public void setDeliveryLocation(String deliveryLocation) {
     this.deliveryLocation = deliveryLocation;
 }

 // Gets the order status
 public String getOrderStatus() {
     return orderStatus;
 }

 // Sets the order status
 public void setOrderStatus(String orderStatus) {
     this.orderStatus = orderStatus;
 }

 // Gets the total amount for the order
 public Double getTotalAmount() {
     return totalAmount;
 }

 // Sets the total amount for the order
 public void setTotalAmount(Double totalAmount) {
     this.totalAmount = totalAmount;
 }

 // Gets the last modification timestamp
 public LocalDateTime getModifiedAt() {
     return modifiedAt;
 }

 // Sets the last modification timestamp
 public void setModifiedAt(LocalDateTime modifiedAt) {
     this.modifiedAt = modifiedAt;
 }

 // Gets the user who last modified the order
 public String getModifiedBy() {
     return modifiedBy;
 }

 // Sets the user who last modified the order
 public void setModifiedBy(String modifiedBy) {
     this.modifiedBy = modifiedBy;
 }

 // Updates the total amount for the order based on the sum of the prices of order details
 public void updateTotalAmount() {
     this.totalAmount = orderDetails.stream()
                         .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                         .sum();
 }
}
