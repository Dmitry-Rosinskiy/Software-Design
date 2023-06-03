package com.example.RestaurantAPI.User.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User with this email already exists")
public class UserEmailExistsException extends RuntimeException {
    public UserEmailExistsException() {
        super();
    }
}
