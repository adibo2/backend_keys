package com.example.backend_keys.customer;

public record CustomerRegistrationRequest(
        Integer Id,
        String name,
        String email,
        Integer age
) {
}
