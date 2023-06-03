package com.example.RestaurantAPI.User.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Role does not exist")
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super();
    }
}
