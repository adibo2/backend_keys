package com.example.backend_keys.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =  HttpStatus.CONFLICT)
public class DuplicateRessource extends RuntimeException{
    public DuplicateRessource(String message) {
        super(message);
    }
}
