package com.example.backend_keys.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class CustomerIdNotFound extends RuntimeException {
    public CustomerIdNotFound(String message) {
        super(message);
    }
}
