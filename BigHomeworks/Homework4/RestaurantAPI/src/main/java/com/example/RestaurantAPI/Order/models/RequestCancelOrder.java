package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestCancelOrder {
    @Schema(description = "Токен (JWT)")
    private String token;

    @Schema(description = "Идентификатор заказа")
    private int orderID;

    public RequestCancelOrder(String token, int orderID) {
        this.token = token;
        this.orderID = orderID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
