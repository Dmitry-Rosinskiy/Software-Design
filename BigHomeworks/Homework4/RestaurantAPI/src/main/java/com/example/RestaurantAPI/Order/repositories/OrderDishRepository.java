package com.example.RestaurantAPI.Order.repositories;

import com.example.RestaurantAPI.Order.models.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
    @Query("SELECT od FROM OrderDish od WHERE od.orderID = ?1")
    List<OrderDish> findOrderDishesByOrderID(int orderID);
}
