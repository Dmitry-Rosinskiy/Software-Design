package com.example.RestaurantAPI.Order.services;

import com.example.RestaurantAPI.Order.models.OrderDish;
import com.example.RestaurantAPI.Order.repositories.OrderDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDishService {

    private final OrderDishRepository orderDishRepository;
    private final DishService dishService;

    @Autowired
    public OrderDishService(OrderDishRepository orderDishRepository, DishService dishService) {
        this.orderDishRepository = orderDishRepository;
        this.dishService = dishService;
    }

    public void addNewOrderDish(int id, Integer dishID, int quantity, double price) {
        OrderDish orderDish = new OrderDish(id, dishID, quantity, price);
        orderDishRepository.save(orderDish);
        dishService.removeDishQuantity(dishID, quantity);
    }

    public long getOrderSize(int orderID) {
        List<OrderDish> orderDishes = orderDishRepository.findOrderDishesByOrderID(orderID);
        long size = 0;
        for (OrderDish orderDish : orderDishes) {
            size += orderDish.getQuantity();
        }
        return size;
    }

    public List<OrderDish> getOrderDishesByOrderID(int orderID) {
        return orderDishRepository.findOrderDishesByOrderID(orderID);
    }
}
