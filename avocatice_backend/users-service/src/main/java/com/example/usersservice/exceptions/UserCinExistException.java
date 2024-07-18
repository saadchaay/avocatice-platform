package com.example.usersservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserCinExistException extends Exception {
    public UserCinExistException(String message) {
        super(message);
    }
}
