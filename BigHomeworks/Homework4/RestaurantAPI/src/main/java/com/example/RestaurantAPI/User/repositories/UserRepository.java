package com.example.RestaurantAPI.User.repositories;

import com.example.RestaurantAPI.User.models.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<RestaurantUser, Integer> {
    @Query("SELECT u FROM RestaurantUser u WHERE u.id = ?1")
    Optional<RestaurantUser> findUserByID(int userID);
    @Query("SELECT u FROM RestaurantUser u WHERE u.username = ?1")
    Optional<RestaurantUser> findUserByName(String name);

    @Query("SELECT u FROM RestaurantUser u WHERE u.email = ?1")
    Optional<RestaurantUser> findUserByEmail(String email);
}
