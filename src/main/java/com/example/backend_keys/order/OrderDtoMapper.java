package com.example.backend_keys.order;

import java.util.function.Function;

public class OrderDtoMapper implements Function<Order,OrderDto> {
    @Override
    public OrderDto apply(Order order) {
        return new OrderDto(
                order.getId(),
                order.getEmail(),
                order.getOrderDetails(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getOrderStatus()
        );
    }
}
