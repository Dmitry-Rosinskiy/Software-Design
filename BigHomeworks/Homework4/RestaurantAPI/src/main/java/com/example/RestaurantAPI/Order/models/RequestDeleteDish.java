package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestDeleteDish {
    @Schema(description = "Токен (JWT)")
    private String token;

    @Schema(description = "Идентификатор блюда")
    private int dishID;

    public RequestDeleteDish(String token, int dishID) {
        this.token = token;
        this.dishID = dishID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }
}
