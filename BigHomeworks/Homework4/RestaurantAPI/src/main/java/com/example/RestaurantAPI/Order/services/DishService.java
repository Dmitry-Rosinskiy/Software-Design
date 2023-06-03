package com.example.RestaurantAPI.Order.services;

import com.example.RestaurantAPI.Order.models.Dish;
import com.example.RestaurantAPI.Order.repositories.DishRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Optional<Dish> getDishByID(int dishID) {
        return dishRepository.findDishByID(dishID);
    }

    public int getDishQuantityByID(int id) {
        Optional<Dish> dish = dishRepository.findDishByID(id);
        if (dish.isPresent()) {
            return dish.get().getQuantity();
        } else {
            return -1;
        }
    }

    public boolean hasInStock(Map<Integer, Integer> dishList) {
        for (Integer dishID : dishList.keySet()) {
            Optional<Dish> dish = getDishByID(dishID);
            if (!dish.isPresent()) {
                return false;
            }
            int quantity = getDishQuantityByID(dishID);
            if (quantity < dishList.get(dishID)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void removeDishQuantity(int id, int quantity) {
        dishRepository.removeDishQuantity(id, quantity);
    }

    public List<Dish> getMenu() {
        return dishRepository.findAllAvailableDishes();
    }

    public Dish addNewDish(String name, String description, double price, int quantity) {
        Dish dish = new Dish(name, description, price, quantity);
        return dishRepository.save(dish);
    }

    @Transactional
    public void updateDish(int id, String name, String description, double price, int quantity) {
        dishRepository.updateDish(id, name, description, price, quantity);
    }

    public void removeDishByID(int id) {
        Optional<Dish> dish = getDishByID(id);
        if (dish.isPresent()) {
            dishRepository.delete(dish.get());
        }

    }
}
