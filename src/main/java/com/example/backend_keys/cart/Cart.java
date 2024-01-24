package com.example.backend_keys.cart;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    //it like auto increment
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    @Column(name = "cart_id")
    private Integer id;

    @Column(name = "totalProduct",nullable = false)
    private int totalProduct;

    @Column(nullable = false)
    private Double totalPrice ;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id"
            ,foreignKey = @ForeignKey(name="customer_id_fk"))
    private Customer customer;

    @OneToMany(mappedBy = "cart",orphanRemoval = true
            ,cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private List<Product> product=new ArrayList<>();


}
