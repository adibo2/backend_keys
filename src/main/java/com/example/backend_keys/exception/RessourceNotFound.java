package com.example.backend_keys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//happen during the normal execution of ou programm
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RessourceNotFound extends RuntimeException {
    public RessourceNotFound(String message) {
        super(message);
    }
}
