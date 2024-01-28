package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "totalProduct",nullable = false)
    private int totalProduct;

    @Column(nullable = false)
    private Double totalPrice ;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name="customer_id_fk"))
    private Customer customer;

    @OneToMany(mappedBy = "cart",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Cartitem> cartitemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Cartitem> getCartitemList() {
        return cartitemList;
    }

    public void setCartitemList(List<Cartitem> cartitemList) {
        this.cartitemList = cartitemList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", totalProduct=" + totalProduct +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                ", cartitemList=" + cartitemList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return totalProduct == cart.totalProduct && Objects.equals(id, cart.id) && Objects.equals(totalPrice, cart.totalPrice) && Objects.equals(customer, cart.customer) && Objects.equals(cartitemList, cart.cartitemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalProduct, totalPrice, customer, cartitemList);
    }
}
