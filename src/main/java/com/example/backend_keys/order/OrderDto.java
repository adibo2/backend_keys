package com.example.backend_keys.order;

import com.example.backend_keys.orderDetail.OrderDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(Integer orderId, String email, List<OrderDetail> orderDetails
,LocalDateTime orderDate,double totalPrice,String orderStatus) {
}
