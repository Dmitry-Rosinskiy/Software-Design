package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ResponseOrderInfo {
    @Schema(description = "Заказ")
    private RestaurantOrder order;

    @Schema(description = "Список блюд")
    private List<OrderDish> orderDishes;

    public ResponseOrderInfo(RestaurantOrder order, List<OrderDish> orderDishes) {
        this.order = order;
        this.orderDishes = orderDishes;
    }

    public RestaurantOrder getOrder() {
        return order;
    }

    public void setOrder(RestaurantOrder order) {
        this.order = order;
    }

    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
    }
}
