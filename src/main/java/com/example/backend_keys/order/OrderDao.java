package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;

import java.util.List;

public interface OrderDao {
    OrderDto save(Integer cartId,String email);

    List<OrderDto> findAll(String username);

    void cancelOrder(Integer Id);

    List<OrderDto> getOrdersByUser(String email);

    OrderDto getOrder(String email,Integer orderId);
}
