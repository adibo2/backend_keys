package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerRepisotory;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.orderDetail.OrderDetail;
import com.example.backend_keys.orderDetail.OrderDetailRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService implements OrderDao{
    private OrderRepo orderRepo;
    private CustomerRepisotory customerRepisotory;

    private OrderDetailRepo orderDetailRepo;

    public OrderService(OrderRepo orderRepo,CustomerRepisotory customerRepisotory,OrderDetailRepo orderDetailRepo) {
        this.orderRepo = orderRepo;
        this.customerRepisotory=customerRepisotory;
        this.orderDetailRepo=orderDetailRepo;

    }

    @Override
    public Order save(Cart cart) {
        Order order=new Order();

        order.setOrderDate(new Date());
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderStatus("pending");
        List<OrderDetail> orderDetails=new ArrayList<>();
        for(Cartitem cartitem : cart.getCartitemList()){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartitem.getProduct());
            orderDetailRepo.save(orderDetail);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);
        //delete cartt id

        return orderRepo.save(order);
    }

    @Override
    public List<Order> findAll(String username) {
        Customer customerName=customerRepisotory.findAllByName(username);
        List<Order> orders=customerName.getOrders();
        return  orders;
    }

    @Override
    public void cancelOrder(Integer Id) {

    }

    @Override
    public List<Order> getOrderByUser(String Id) {
        List<Order> orders=orderRepo.findAllById(Id);

        if(orders.size() ==0){
            throw  new ApiException("No orders placed yet by the user with email: " + Id);
        }
        return orders;
    }
    @Override
    public List<OrderDTO> getOrdersByUser(String emailId) {
        List<Order> orders = orderRepo.findAllByEmail(emailId);

        List<OrderDTO> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());

        if (orderDTOs.size() == 0) {
            throw new APIException("No orders placed yet by the user with email: " + emailId);
        }

        return orderDTOs;
    }

    @Override
    public OrderDTO getOrder(String emailId, Long orderId) {

        Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);

        if (order == null) {
            throw new ResourceNotFoundException("Order", "orderId", orderId);
        }

        return modelMapper.map(order, OrderDTO.class);
    }


}
