package com.example.RestaurantAPI.User.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User email is not valid")
public class UserEmailValidationException extends RuntimeException {
    public UserEmailValidationException() {
        super();
    }
}
