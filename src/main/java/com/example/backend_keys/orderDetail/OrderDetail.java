package com.example.backend_keys.orderDetail;

import com.example.backend_keys.order.Order;
import com.example.backend_keys.product.Product;
import jakarta.persistence.*;

@Entity(name = "orderDetail")
@Table(name = "orderDetail")
public class OrderDetail {
    @Id
    //it like auto increment
    @SequenceGenerator(
            name = "orderDetail_id_sequence",
            sequenceName = "orderDetail_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderDetail_id_sequence"
    )
    @Column(name = "id")
    private Integer id;




    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id",referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "order_id_fk"
    ))
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id",foreignKey =  @ForeignKey(
            name = "product_id_fk"
    ))
    private Product product;

    @Column
    private double discount;

    @Column
    private Integer quantity;

    @Column
    private double orderedProductPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getOrderedProductPrice() {
        return orderedProductPrice;
    }

    public void setOrderedProductPrice(double orderedProductPrice) {
        this.orderedProductPrice = orderedProductPrice;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
