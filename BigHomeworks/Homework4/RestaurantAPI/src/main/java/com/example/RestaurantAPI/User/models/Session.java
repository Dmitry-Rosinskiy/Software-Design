package com.example.RestaurantAPI.User.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "sessions")
@Schema(name = "Сессия")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Идентификатор")
    private int id;

    @JoinColumn(name = "user_id", table = "restaurant_user", referencedColumnName = "id")
    @Column(name = "user_id")
    @Schema(description = "Идентификатор пользователя")
    private int userID;

    @Column(name = "session_token", length = 255, nullable = false)
    @Schema(description = "Токен (JWT)")
    private String sessionToken;

    @Column(name = "expires_at", nullable = false)
    @Schema(description = "Время окончания")
    private Date expiresAt;

    public Session() {
    }

    public Session(int userID, String sessionToken, Date expiresAt) {
        this.userID = userID;
        this.sessionToken = sessionToken;
        this.expiresAt = expiresAt;
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

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", userID=" + userID +
                ", sessionToken='" + sessionToken + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
