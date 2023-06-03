package com.example.RestaurantAPI.Order.repositories;

import com.example.RestaurantAPI.Order.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("SELECT d FROM Dish d WHERE d.id = ?1")
    Optional<Dish> findDishByID(int id);

    @Modifying
    @Query("UPDATE Dish d SET d.quantity = d.quantity - ?2 WHERE d.id = ?1")
    void removeDishQuantity(Integer dishID, int quantity);

    @Query("SELECT d FROM Dish d WHERE d.quantity > 0")
    List<Dish> findAllAvailableDishes();

    @Modifying
    @Query("UPDATE Dish d SET d.name = ?2, d.description = ?3, d.price = ?4, d.quantity = ?5 WHERE d.id = ?1")
    void updateDish(int id, String name, String description, double price, int quantity);
}
