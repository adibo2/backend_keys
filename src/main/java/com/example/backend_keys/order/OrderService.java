package com.example.backend_keys.order;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.cart.CartRepository;
import com.example.backend_keys.cart.CartService;
import com.example.backend_keys.customer.CustomerRepisotory;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.orderDetail.OrderDetail;
import com.example.backend_keys.orderDetail.OrderDetailRepo;
import com.example.backend_keys.product.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService implements OrderDao{
    private OrderRepo orderRepo;
    private CustomerRepisotory customerRepisotory;

    private OrderDetailRepo orderDetailRepo;
    private CartRepository cartRepository;

    private OrderDtoMapper orderDtoMapper;

    private CartService cartService;

    public OrderService(OrderRepo orderRepo, CustomerRepisotory customerRepisotory, CartService cartService,
                        OrderDetailRepo orderDetailRepo, CartRepository cartRepository, OrderDtoMapper orderDtoMapper) {
        this.orderRepo = orderRepo;
        this.customerRepisotory=customerRepisotory;
        this.orderDetailRepo=orderDetailRepo;
        this.cartRepository = cartRepository;
        this.orderDtoMapper=orderDtoMapper;
        this.cartService=cartService;

    }

    @Override
    public OrderDto save(Integer cartId,String email) {
        Order order=new Order();
        Cart cart= cartRepository.findCartByEmailAndCartId(email,cartId).orElseThrow(()->{
           return new RessourceNotFound("customer with email %s".formatted(email));
        });

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
            cartService.deleteProductFromCart(cartId,product.getId());
            product.setStock(product.getStock() - quantity);


        });
        OrderDto orderDto=orderDtoMapper.apply(savedOrder);

        return orderDto;

        //delete cartt id

    }


    @Override
    public void cancelOrder(Integer Id) {

    }



    @Override
    public OrderDto getOrder(String email, Integer orderId) {
        Order order=orderRepo.findOrderByEmailAndId(email,orderId);
        if(order == null){
            throw new RessourceNotFound("customer with email %s %s not found".formatted(email,orderId));
        }
        return orderDtoMapper.apply(order);
    }

    @Override
    public OrderDto updateOrder(String email, Integer orderId, String orderStatus) {
        Order order=orderRepo.findOrderByEmailAndId(email,orderId);
        if(order == null){
            throw new RessourceNotFound("customer with email %s %s not found".formatted(email,orderId));
        }
        order.setOrderStatus(orderStatus);

        return orderDtoMapper.apply(order);
    }


}
