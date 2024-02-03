package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.cart.CartRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerRepisotory;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.orderDetail.OrderDetail;
import com.example.backend_keys.orderDetail.OrderDetailRepo;
import com.example.backend_keys.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements OrderDao{
    private OrderRepo orderRepo;
    private CustomerRepisotory customerRepisotory;

    private OrderDetailRepo orderDetailRepo;
    private CartRepo cartRepo;

    public OrderService(OrderRepo orderRepo,CustomerRepisotory customerRepisotory,
                        OrderDetailRepo orderDetailRepo,CartRepo cartRepo) {
        this.orderRepo = orderRepo;
        this.customerRepisotory=customerRepisotory;
        this.orderDetailRepo=orderDetailRepo;
        this.cartRepo=cartRepo;

    }

    @Override
    public OrderDto save(Integer cartId,String email) {
        Order order=new Order();
        Cart cart= cartRepo.findCartByEmailAndCartId(email,cartId);
        if(cart ==null){
            throw new RessourceNotFound("customer with email %s".formatted(email));
        }
        order.setEmail(email);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted !");

        Order savedOrder=orderRepo.save(order);

        List<OrderDetail> orderDetails=cart.getCartitemList().stream()
                .map(cartitem -> {
                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setQuantity(cartitem.getQuantity());
                    orderDetail.setDiscount(cartitem.getDiscount());
                    orderDetail.setProduct(cartitem.getProduct());
                    orderDetail.setOrder(savedOrder);
                    return orderDetail;
                }).collect(Collectors.toList());

        if(orderDetails.size() == 0){
            throw new ApiException("Cart is empty ");
        }
         orderDetails= orderDetailRepo.saveAll(orderDetails);

        cart.getCartitemList().forEach(el->{
            int quantity= el.getQuantity();
            Product product=el.getProduct();
        });


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





}
