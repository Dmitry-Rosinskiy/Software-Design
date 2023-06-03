package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

public class RequestCreateDish {
    @Schema(description = "Токен (JWT)")
    private String token;

    @Schema(description = "Название")
    @Size(min = 1, max = 100)
    private String name;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Цена")
    private double price;

    @Schema(description = "Количество")
    private int quantity;

    public RequestCreateDish(String token, String name, String description, double price, int quantity) {
        this.token = token;
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
