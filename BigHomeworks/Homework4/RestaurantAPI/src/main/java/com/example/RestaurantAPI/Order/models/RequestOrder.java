package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public class RequestOrder {
    @Schema(description = "Токен (JWT)")
    private String token;

    @Schema(description = "Список блюд")
    private Map<Integer, Integer> dishList;

    @Schema(description = "Особые запросы")
    private String specialRequests;

    public RequestOrder(String token, Map<Integer, Integer> dishList, String specialRequests) {
        this.token = token;
        this.dishList = dishList;
        this.specialRequests = specialRequests;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<Integer, Integer> getDishList() {
        return dishList;
    }

    public void setDishList(Map<Integer, Integer> dishList) {
        this.dishList = dishList;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }
}
