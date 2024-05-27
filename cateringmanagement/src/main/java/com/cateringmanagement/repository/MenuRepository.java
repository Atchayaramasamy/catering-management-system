package com.cateringmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cateringmanagement.entity.MenuList;
//Repository interface for MenuList entity
@Repository
public interface MenuRepository extends JpaRepository<MenuList, Integer> {
}