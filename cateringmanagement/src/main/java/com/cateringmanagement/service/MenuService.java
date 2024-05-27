package com.cateringmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cateringmanagement.dao.MenuDAO;
import com.cateringmanagement.entity.MenuList;

//Service class for menu-related operations
@Service
public class MenuService {

 private final MenuDAO menuDao;

 // Constructor injection of MenuDAO
 @Autowired
 public MenuService(MenuDAO menuDao) {
     this.menuDao = menuDao;
 }

 // Method to retrieve all menu items
 public List<MenuList> getAllMenuItems() {
     return menuDao.getAllMenuItems();
 }

 // Method to retrieve a menu item by its ID
 public MenuList getMenuById(int foodId) {
     return menuDao.getMenuById(foodId);
 }

 // Method to create a new menu item
 public MenuList createMenuItem(MenuList menu) {
     return menuDao.createMenuItem(menu);
 }

 // Method to update an existing menu item
 public MenuList updateMenuItem(int foodId, MenuList menu) {
     return menuDao.updateMenuItem(foodId, menu);
 }

 // Method to delete a menu item
 public void deleteMenuItem(int foodId) {
     menuDao.deleteMenuItem(foodId);
 }
}