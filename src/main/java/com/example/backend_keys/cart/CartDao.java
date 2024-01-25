package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;

import java.util.Optional;

public interface CartDao {
    Cart addtoCart(Product product, int quantity, Customer customer);

    Cart deleteItemFromCart(Product product, Customer customer);

}
