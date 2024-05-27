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

import com.cateringmanagement.entity.MenuList;
import com.cateringmanagement.service.MenuService;

//Controller class for handling menu-related endpoints
@RestController
@RequestMapping("/menu")
public class MenuController {
 
 // Injecting MenuService to handle menu-related operations
 @Autowired
 private MenuService menuService;
 
 // Endpoint to display all menu items
 @GetMapping("/displayAll")
 public ResponseEntity<List<MenuList>> getAllMenuItems() {
     List<MenuList> menuItems = menuService.getAllMenuItems();
     return ResponseEntity.ok(menuItems);
 }
 
 // Endpoint to get a menu item by its ID
 @GetMapping("/byId/{foodId}")
 public ResponseEntity<MenuList> getMenuById(@PathVariable int foodId) {
     MenuList menuItem = menuService.getMenuById(foodId);
     if (menuItem != null) {
         return ResponseEntity.ok(menuItem);
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 
 // Endpoint to create a new menu item
 @PostMapping("/create")
 public ResponseEntity<MenuList> createMenuItem(@RequestBody MenuList menu) {
     MenuList createdMenuItem = menuService.createMenuItem(menu);
     return ResponseEntity.ok(createdMenuItem);
 }
 
 // Endpoint to update an existing menu item
 @PutMapping("/update/{foodId}")
 public ResponseEntity<MenuList> updateMenuItem(@PathVariable int foodId, @RequestBody MenuList menu) {
     MenuList updatedMenuItem = menuService.updateMenuItem(foodId, menu);
     if (updatedMenuItem != null) {
         return ResponseEntity.ok(updatedMenuItem);
     } else {
         return ResponseEntity.notFound().build();
     }
 }
 
 // Endpoint to delete a menu item
 @DeleteMapping("/delete/{foodId}")
 public ResponseEntity<Void> deleteMenuItem(@PathVariable int foodId) {
     menuService.deleteMenuItem(foodId);
     return ResponseEntity.noContent().build();
 }
}
