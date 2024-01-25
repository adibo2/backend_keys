package com.example.backend_keys.cartitems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cartitem")
public interface CartItemRepo extends JpaRepository<Cartitem,Integer> {
}
