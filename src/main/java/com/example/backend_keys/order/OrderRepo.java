package com.example.backend_keys.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findAllById(String Id);
}
