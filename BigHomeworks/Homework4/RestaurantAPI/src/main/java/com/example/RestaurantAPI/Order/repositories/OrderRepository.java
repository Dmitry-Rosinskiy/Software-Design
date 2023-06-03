package com.example.RestaurantAPI.Order.repositories;

import com.example.RestaurantAPI.Order.models.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<RestaurantOrder, Integer> {
    @Query("SELECT o FROM RestaurantOrder o WHERE o.id = ?1")
    Optional<RestaurantOrder> findOrderByID(int id);
}
