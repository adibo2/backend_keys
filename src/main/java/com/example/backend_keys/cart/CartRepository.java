package com.example.backend_keys.cart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart,Integer> {

    @Query("select c from cart c where c.customer.email = ?1 and c.id = ?2")
    Optional<Cart>  findCartByEmailAndCartId(String email,Integer cartId);

     Optional<Cart> findCartById(Integer id);

     @Query("select c from cart c JOIN FETCH c.cartitemList ci JOIN FETCH ci.product p where p.id=?1")
     List<Cart> findCartByProductId(Integer productId);


}
