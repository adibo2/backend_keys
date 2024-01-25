package com.example.backend_keys.cartitems;


import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.product.Product;
import jakarta.persistence.*;

@Entity(name = "cartItem")
public class Cartitem {
    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column
    private double totalPrice;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="Cart_id",referencedColumnName = "id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
