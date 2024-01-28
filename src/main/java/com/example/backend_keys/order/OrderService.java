package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerRepisotory;

import java.util.List;

public class OrderService implements OrderDao{
    private OrderRepo orderRepo;
    private CustomerRepisotory customerRepisotory;

    public OrderService(OrderRepo orderRepo,CustomerRepisotory customerRepisotory) {
        this.orderRepo = orderRepo;
        this.customerRepisotory=customerRepisotory;

    }

    @Override
    public Order save(Cart cart) {
        return null;
    }

    @Override
    public List<Order> findAll(String username) {
        Customer customerName=customerRepisotory.findAllByName(username);
        List<Order> orders=customerName.getOrders();
        return  orders;
    }
}
