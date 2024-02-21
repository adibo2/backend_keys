package com.example.backend_keys.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    @Query("SELECT o from orders o where o.email=?1 and o.id=?2")
    Order findOrderByEmailAndId(String email,Integer orderId);
    List<Order> findAllById(Integer id);
}
