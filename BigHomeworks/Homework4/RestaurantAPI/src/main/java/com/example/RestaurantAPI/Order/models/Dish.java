package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Идентификатор")
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    @Schema(description = "Название")
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "description")
    @Schema(description = "Описание")
    private String description;

    @Column(name = "price", length = 10, precision = 2, nullable = false)
    @Schema(description = "Цена")
    private double price;

    @Column(name = "quantity", nullable = false)
    @Schema(description = "Количество")
    private int quantity;

    public Dish() {
    }

    public Dish(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
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
