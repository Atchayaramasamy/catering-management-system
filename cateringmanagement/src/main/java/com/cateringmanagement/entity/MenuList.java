package com.cateringmanagement.entity;

// Import necessary JPA packages
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The MenuList class represents a menu item in the catering management system.
 * It includes details such as the food item's name and price.
 */

@Entity  // Indicates that this is a JPA entity
@Table(name="menu")  // Maps the entity to the "menu" table in the database
public class MenuList {

    @Id  // Primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated value for the primary key
    @Column(name = "food_id")  // Maps to the "food_id" column in the table
    private int foodId;
    
    @Column(name = "food_name")  // Maps to the "food_name" column in the table
    private String foodName;
    
    @Column(name = "food_price")  // Maps to the "food_price" column in the table
    private double price;

    // Default constructor
    public MenuList() {
    }

    // Constructor to initialize food name and price
    public MenuList(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuList [foodId=" + foodId + ", foodName=" + foodName + ", price=" + price + "]";
    }

    // Getters and Setters
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
