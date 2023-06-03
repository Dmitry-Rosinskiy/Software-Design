package com.example.RestaurantAPI.User.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestUserInfo {
    @Schema(description = "Токен (JWT)")
    private String token;
    @Schema(description = "Идентификатор пользователя")
    private int userID;

    RequestUserInfo(String token, int userID) {
        this.token = token;
        this.userID = userID;
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
}
