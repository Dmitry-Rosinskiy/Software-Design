package com.example.RestaurantAPI.User.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "users")
public class RestaurantUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Идентификатор")
    private int id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    @Schema(description = "Имя")
    @Size(min = 1, max = 50)
    private String username;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    @Schema(description = "Электронная почта")
    @Size(max = 100)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    @Schema(description = "Хеш пароля")
    private String passwordHash;

    @Column(name = "role", length = 10, nullable = false)
    @Schema(description = "Роль")
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at")
    @Schema(description = "Время создания")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Schema(description = "Время обновления")
    private Date updatedAt;

    public RestaurantUser() {
    }

    public RestaurantUser(String username, String email, String passwordHash, Role role) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date created_at) {
        this.createdAt = created_at;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updatedAt = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
