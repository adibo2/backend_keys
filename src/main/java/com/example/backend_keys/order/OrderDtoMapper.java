package com.example.backend_keys.order;

import com.example.backend_keys.orderDetail.OrderDetailMapper;

import java.util.function.Function;

public class OrderDtoMapper implements Function<Order,OrderDto> {

    private final OrderDetailMapper orderDetailMapper;

    public OrderDtoMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public OrderDto apply(Order order) {
        return new OrderDto(
                order.getId(),
                order.getEmail(),
               order.getOrderDetails().stream()
                       .map(el->orderDetailMapper.apply(el)).toList(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getOrderStatus()
        );
    }
}
