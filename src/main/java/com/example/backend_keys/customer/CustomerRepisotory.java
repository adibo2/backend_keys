package com.example.backend_keys.customer;

import org.springframework.data.jpa.repository.JpaRepository;

//extend to perform crud operation
//what generic we want jparepisotory to act on
public interface CustomerRepisotory extends JpaRepository<Customer,Integer> {
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById (Integer id);
}
