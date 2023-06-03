package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestMenu {
    @Schema(description = "Токен (JWT)")
    private String token;

    public RequestMenu() {
    }

    public RequestMenu(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
