package com.example.backend_keys.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cart")
public interface CartRepo extends JpaRepository<Cart,Integer> {
}
