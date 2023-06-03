package com.example.RestaurantAPI.Order.services;

import com.example.RestaurantAPI.Order.models.ChangeOrderStatus;
import com.example.RestaurantAPI.Order.models.Status;
import com.example.RestaurantAPI.Order.models.Dish;
import com.example.RestaurantAPI.Order.models.RestaurantOrder;
import com.example.RestaurantAPI.Order.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DishService dishService;
    private final OrderDishService orderDishService;

    @Autowired
    public OrderService(OrderRepository orderRepository, DishService dishService, OrderDishService orderDishService) {
        this.orderRepository = orderRepository;
        this.dishService = dishService;
        this.orderDishService = orderDishService;
    }

    public Optional<RestaurantOrder> getOrderByID(int orderID) {
        return orderRepository.findOrderByID(orderID);
    }

    public RestaurantOrder addNewOrder(int userID, Map<Integer, Integer> dishList, String specialRequests) {
        RestaurantOrder order = orderRepository.save(new RestaurantOrder(userID, Status.PENDING, specialRequests));

        for (Integer dishID : dishList.keySet()) {
            Optional<Dish> dish = dishService.getDishByID(dishID);
            if (dish.isPresent()) {
                int quantity = dishList.get(dishID);
                double price = dish.get().getPrice();
                orderDishService.addNewOrderDish(order.getID(), dishID, quantity, price);
            }
        }
        order = orderRepository.save(order);

        Thread task = new Thread(new ChangeOrderStatus(order.getID(), this, orderDishService));
        task.start();

        return order;
    }

    @Transactional
    public void changeOrderStatus(int id, Status status) {
        Optional<RestaurantOrder> order = getOrderByID(id);
        if (order.isPresent()) {
            order.get().setStatus(status);
            orderRepository.saveAndFlush(order.get());
        }
    }
}
