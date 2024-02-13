package com.example.backend_keys.cartitems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartItemRepo extends JpaRepository<Cartitem,Integer> {

    @Query("select c from  cartItem c where c.cart.id=?1 and  c.product.id=?2")
    Cartitem findCartItemByProductIdAndCartId(Integer cartId, Integer productId);

    @Modifying
    @Query("DELETE from cartItem c where c.cart.id=?1 and c.product.id=?2")
    void deleteCartItemByProductIdAndCartId(Integer cartId, Integer productId);
}
