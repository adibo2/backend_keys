package com.example.backend_keys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestvalidationException extends RuntimeException {
    public RequestvalidationException(String message) {
        super(message);
    }
}
