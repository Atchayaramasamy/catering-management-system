package com.cateringmanagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cateringmanagement.entity.User;

//Repository interface for User entity
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 // Method to find a user by username
 User findByUserName(String username);

 // Method to find a user by username and password
 User findByUserNameAndPassword(String userName, String password);

 // Method to find a user by ID and not marked as deleted
 Optional<User> findByUserIdAndDeletedFalse(int userId);
}