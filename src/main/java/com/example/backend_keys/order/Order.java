package com.example.backend_keys.order;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.orderDetail.OrderDetail;
import jakarta.persistence.*;

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
    private Date orderDate;

    @Column
    private double totalPrice;

    @Column
    private String orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",nullable = false,referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "customer_id_fk"
            ))
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
