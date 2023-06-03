package com.example.RestaurantAPI.User.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User name can not be longer than 50 characters")
public class UserNameLengthException extends RuntimeException {
    public UserNameLengthException() {
        super();
    }
}
