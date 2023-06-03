package com.example.RestaurantAPI;

import com.example.RestaurantAPI.Order.models.Dish;
import com.example.RestaurantAPI.Order.repositories.DishRepository;
import com.example.RestaurantAPI.User.models.RestaurantUser;
import com.example.RestaurantAPI.User.models.Role;
import com.example.RestaurantAPI.User.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RestaurantConfig {
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, DishRepository dishRepository) {
        return args -> {
            // После первого запуска закомментируйте строки 20-52, чтобы ничего больше не добавлялось в базу данных
            RestaurantUser manager = new RestaurantUser(
                    "Manager",
                    "manager@gmail.com",
                    "6ee4a469cd4e91053847f5d3fcb61dbcc91e8f0ef10be7748da4c4a1ba382d17", // password - manager
                    Role.MANAGER);
            RestaurantUser chef = new RestaurantUser(
                    "Chef",
                    "chef@gmail.com",
                    "f59ac0828b9a32293b348e398a0efd342b1e4377a687f3a9055ee2871dff35e4", // password - chef
                    Role.CHEF);
            RestaurantUser customer = new RestaurantUser(
                    "Customer",
                    "customer@gmail.com",
                    "b6c45863875e34487ca3c155ed145efe12a74581e27befec5aa661b8ee8ca6dd", // password - customer
                    Role.CUSTOMER);
            userRepository.saveAll(List.of(manager, chef, customer));
            Dish burger = new Dish(
                    "Burger",
                    "With cheese",
                    50,
                    30);
            Dish frenchFries = new Dish(
                    "French fries",
                    "Medium size",
                    30,
                    50
            );
            Dish coke = new Dish(
                    "Coke",
                    "With ice",
                    25,
                    40);
            dishRepository.saveAll(List.of(burger, frenchFries, coke));
        };
    }
}
