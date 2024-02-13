package com.example.backend_keys.orderDetail;

import com.example.backend_keys.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {


}
