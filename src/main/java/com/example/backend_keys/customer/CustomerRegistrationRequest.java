package com.example.backend_keys.customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age
) {
}
