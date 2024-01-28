package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;

import java.util.Optional;

public interface CartDao {

    Cart addtoCart(Integer cartId, Integer productId, int quantity);

    Cart deleteItemFromCart(Product product, Customer customer);

    CartDto getCart(Integer id);

    void updateProductInCarts(Integer cartId, Integer productId,int quantity);


}
