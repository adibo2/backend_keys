package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;
import org.springframework.data.relational.core.sql.In;

import java.util.Optional;

public interface CartDao {

    Cart addtoCart(Integer cartId, Integer productId, int quantity);

    String deleteProductFromCart(Integer cartId, Integer productId);

    CartDto getCart(Integer id);

    void updateProductInCarts(Integer cartId, Integer productId,int quantity);



}
