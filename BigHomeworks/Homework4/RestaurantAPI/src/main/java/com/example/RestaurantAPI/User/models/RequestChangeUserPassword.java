package com.example.RestaurantAPI.User.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestChangeUserPassword {
    @Schema(description = "Токен (JWT)")
    private String token;
    @Schema(description = "Старый пароль")
    private String oldPassword;
    @Schema(description = "Новый пароль")
    private String newPassword;

    public RequestChangeUserPassword(String token, String oldPassword, String newPassword) {
        this.token = token;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
