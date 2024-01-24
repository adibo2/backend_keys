package com.example.backend_keys.product;

import com.example.backend_keys.cart.Cart;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "product")
@Table(name = "product")
public class Product {
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
    @Column(name = "product_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique=true)
    private String slug;

    @Column(
            unique = true
    )
    private String Image;

    @Column(nullable = false)

    private String alt;


    @Column(nullable = false)

    private String meta;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer Discount;

    @ManyToOne
    @JoinColumn(
            name = "cart_id",
            nullable = false,
            referencedColumnName = "cart_id",
            foreignKey = @ForeignKey(
                    name = "cart_product_fk"
            )
    )
    private Cart cart;

    public Product() {
    }

    public Product(Integer id, String name, String slug, String image, String alt, String meta, Integer stock, BigDecimal price, Integer discount) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        Image = image;
        this.alt = alt;
        this.meta = meta;
        this.stock = stock;
        this.price = price;
        Discount = discount;
    }

    public Product(String name, String slug, String image, String alt, String meta, Integer stock, BigDecimal price, Integer discount) {
        this.name = name;
        this.slug = slug;
        Image = image;
        this.alt = alt;
        this.meta = meta;
        this.stock = stock;
        this.price = price;
        Discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }



    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return Discount;
    }

    public void setDiscount(Integer discount) {
        Discount = discount;
    }
}
