package com.example.backend_keys.cartitems;


import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.order.Order;
import com.example.backend_keys.product.Product;
import jakarta.persistence.*;

@Entity(name = "cartItem")
@Table(name = "cart_item")
public class Cartitem {
    @Id
    @SequenceGenerator(
            name = "cartItem_id_sequence",
            sequenceName = "cartItem_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cartItem_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @Column
    private double productPrice;

    @Column
    private int quantity;

    @Column
    private double discount;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id",referencedColumnName = "id",foreignKey = @ForeignKey(
            name = "cart_id_fk"))
    private Cart cart;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",referencedColumnName = "id",foreignKey = @ForeignKey(
            name = "product_id_fk"))
    private Product product;

    public Integer getId() {
        return id;
    }

    public Cartitem() {
    }

    public Cartitem( double productPrice, int quantity, double discount, Cart cart, Product product) {
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.cart = cart;
        this.product = product;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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
