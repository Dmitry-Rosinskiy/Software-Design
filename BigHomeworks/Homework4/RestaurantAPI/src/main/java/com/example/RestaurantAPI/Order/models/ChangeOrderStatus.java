package com.example.RestaurantAPI.Order.models;

import com.example.RestaurantAPI.Order.services.OrderDishService;
import com.example.RestaurantAPI.Order.services.OrderService;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

@Transactional
public class ChangeOrderStatus implements Runnable {

    private final int orderID;
    private final OrderService orderService;
    private final OrderDishService orderDishService;


    public ChangeOrderStatus(int orderID, OrderService orderService, OrderDishService orderDishService) {
        this.orderID = orderID;
        this.orderService = orderService;
        this.orderDishService = orderDishService;
    }

    @Transactional
    public void run() {
        try {
            Random random = new Random();
            Thread.sleep((random.nextInt(30 - 10 + 1) + 10) * 1000);
            if (orderService.getOrderByID(orderID).get().getStatus() == Status.CANCELLED) {
                return;
            }
            orderService.changeOrderStatus(orderID, Status.PROCESSING);
            Thread.sleep(orderDishService.getOrderSize(orderID) * 5000);
            if (orderService.getOrderByID(orderID).get().getStatus() == Status.CANCELLED) {
                return;
            }
            orderService.changeOrderStatus(orderID, Status.FINISHED);
        } catch (InterruptedException ignored) {
        }
    }
}
