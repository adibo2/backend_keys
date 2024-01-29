package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;

import java.util.List;

public interface OrderDao {
    Order save(Cart cart);

    List<Order> findAll(String username);

    void cancelOrder(Integer Id);

    List<Order> getOrderByUser(String Id);
}
