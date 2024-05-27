package com.cateringmanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cateringmanagement.entity.MenuList;
import com.cateringmanagement.repository.MenuRepository;

//DAO class for menu-related operations
@Repository
public class MenuDAO {

 @Autowired
 private final MenuRepository menuRepository;

 public MenuDAO(MenuRepository menuRepository) {
     this.menuRepository = menuRepository;
 }

 // Method to retrieve all menu items
 public List<MenuList> getAllMenuItems() {
     return menuRepository.findAll();
 }

 // Method to create a new menu item
 public MenuList createMenuItem(MenuList menu) {
     return menuRepository.save(menu);
 }

 // Method to retrieve a menu item by ID
 public MenuList getMenuById(int foodId) {
     return menuRepository.findById(foodId).orElse(null);
 }

 // Method to update an existing menu item
 public MenuList updateMenuItem(int foodId, MenuList updatedMenu) {
     MenuList existingMenu = menuRepository.findById(foodId).orElse(null);
     if (existingMenu != null) {
         // Update menu fields
         existingMenu.setFoodName(updatedMenu.getFoodName());
         existingMenu.setPrice(updatedMenu.getPrice());
         // Save updated menu
         return menuRepository.save(existingMenu);
     }
     return null;
 }

 // Method to delete a menu item
 public void deleteMenuItem(int foodId) {
     menuRepository.deleteById(foodId);
 }
}