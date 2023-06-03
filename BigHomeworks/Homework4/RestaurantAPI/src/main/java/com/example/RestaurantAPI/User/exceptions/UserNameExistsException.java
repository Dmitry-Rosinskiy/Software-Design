package com.example.RestaurantAPI.User.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User with this name already exists")
public class UserNameExistsException extends RuntimeException {
    public UserNameExistsException() {
        super();
    }
}
