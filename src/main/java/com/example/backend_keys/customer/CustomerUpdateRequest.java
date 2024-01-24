package com.example.backend_keys.customer;

public record CustomerUpdateRequest( String name,
                                     String email,
                                     Integer age) {
}
