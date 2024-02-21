package com.example.backend_keys.orderDetail;

import java.util.function.Function;

public class OrderDetailMapper implements Function<OrderDetail,OrderDetailDto> {

    @Override
    public OrderDetailDto apply(OrderDetail orderDetail) {
        return new OrderDetailDto(
                orderDetail.getId(),
                orderDetail.getProduct(),
                orderDetail.getDiscount(),
                orderDetail.getQuantity(),
                orderDetail.getOrderedProductPrice()
        );
    }
}
