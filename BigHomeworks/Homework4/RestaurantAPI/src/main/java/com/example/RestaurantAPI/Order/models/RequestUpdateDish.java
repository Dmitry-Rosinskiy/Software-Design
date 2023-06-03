package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public class RequestUpdateDish {
    @Schema(description = "Токен (JWT)")
    private String token;

    @Schema(description = "Идентификатор блюда")
    private int dishID;

    @Schema(description = "Название")
    @Size(min = 1, max = 100)
    private String name;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Цена")
    private double price;

    @Schema(description = "Количество")
    private int quantity;

    public RequestUpdateDish(String token, int id, String name, String description, double price, int quantity) {
        this.token = token;
        this.dishID = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public void setDishID(int id) {
        this.dishID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
