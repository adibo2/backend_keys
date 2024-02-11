package com.example.backend_keys.order;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.orderDetail.OrderDetail;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "order")
@Table(name = "order")
public class Order {

    @Id
    //it like auto increment
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Product_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column
    private double totalPrice;

    @Column
    private String orderStatus;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Integer id, LocalDateTime orderDate, double totalPrice, String orderStatus, String email, List<OrderDetail> orderDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.email = email;
        this.orderDetails = orderDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
