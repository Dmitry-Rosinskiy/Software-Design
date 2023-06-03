package com.example.RestaurantAPI.User.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestChangeUserRole {
    @Schema(description = "Токен (JWT)")
    private String token;
    @Schema(description = "Идентификатор пользователя")
    private int userID;
    @Schema(description = "Роль")
    private Role role;

    public RequestChangeUserRole(String token, int userID, Role role) {
        this.token = token;
        this.userID = userID;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
