package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "order_dishes")
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Идентификатор")
    private int id;

    @Column(name = "order_id", nullable = false)
    @Schema(description = "Идентификатор заказа")
    private int orderID;

    @Column(name = "dish_id", nullable = false)
    @Schema(description = "Идентификатор блюда")
    private int dishID;

    @Column(name = "quantity", nullable = false)
    @Schema(description = "Количество блюда")
    private int quantity;

    @Column(name = "price", length = 10, precision = 2, nullable = false)
    @Schema(description = "Цена")
    private double price;

    public OrderDish() {
    }

    public OrderDish(int orderID, int dishID, int quantity, double price) {
        this.orderID = orderID;
        this.dishID = dishID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
