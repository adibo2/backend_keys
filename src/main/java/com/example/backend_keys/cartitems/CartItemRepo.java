package com.example.backend_keys.cartitems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository("cartitem")
public interface CartItemRepo extends JpaRepository<Cartitem,Integer> {

    @Query(value = "SELECT ci FROM cartItem ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2",nativeQuery = true)
    Cartitem findCartItemByProductIdAndCartId(Integer cartId, Integer productId);

    @Query(value = "DELETE from cartItem ci where ci.cart.id=?1 and ci.product.id=?2")
    void deleteCartItemByProductIdAndCartId(Integer cartId, Integer productId);
}
