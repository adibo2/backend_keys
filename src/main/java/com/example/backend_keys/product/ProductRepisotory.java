package com.example.backend_keys.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepisotory extends JpaRepository<Product,Integer> {

    Optional<Product> findProductByName(String name);
}
