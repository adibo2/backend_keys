package com.example.backend_keys.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface CartRepo extends CrudRepository<Cart,Integer> {

    @Query("select ca from Cart where ca.customer.email=?1 and ca.id=?2")
    public Cart findCartByEmailAndCartId(String email,Integer cartId);

    public Optional<Cart> findCartById(Integer id);

}
