package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;

import java.util.List;

public interface OrderDao {
    OrderDto save(Integer cartId,String email);


    void cancelOrder(Integer Id);


    OrderDto getOrder(String email,Integer orderId);
}
