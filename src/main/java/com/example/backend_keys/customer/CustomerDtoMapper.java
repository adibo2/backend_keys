package com.example.backend_keys.customer;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer,CustomerRegistrationRequest> {

    public CustomerRegistrationRequest apply(Customer customer) {
        return new CustomerRegistrationRequest(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAge()
        );
    }
}
