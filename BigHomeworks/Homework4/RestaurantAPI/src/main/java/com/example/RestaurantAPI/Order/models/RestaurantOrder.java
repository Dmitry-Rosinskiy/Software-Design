package com.example.RestaurantAPI.Order.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "orders")
public class RestaurantOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Идентификатор")
    private int id;

    @Column(name = "user_id", nullable = false)
    @Schema(description = "Идентификатор пользователя")
    private int userID;

    @Column(name = "status")
    @Schema(description = "Статус")
    private Status status;

    @Column(name = "special_requests")
    @Schema(description = "Особые запросы")
    private String specialRequests;

    @CreationTimestamp
    @Column(name = "created_at")
    @Schema(description = "Время создания")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "Время обновления")
    private Date updatedAt;

    public RestaurantOrder() {
    }

    public RestaurantOrder(int userID, Status status, String specialRequests) {
        this.userID = userID;
        this.status = status;
        this.specialRequests = specialRequests;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
