package com.example.backend_keys.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("cart")
public interface CartRepo extends JpaRepository<Cart,Integer> {

    @Query("select ca from Cart where ca.customer.email=?1 and ca.id=?2")
    public Cart findCartByEmailAndCartId(String email,Integer cartId);

    public Optional<Cart> findCartById(Integer id);

}
