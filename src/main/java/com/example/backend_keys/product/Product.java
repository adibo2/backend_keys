package com.example.backend_keys.product;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.cartitems.Cartitem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "product")
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(
                name = "product_name_unique",
                columnNames = "name"
        ),
})
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
    @Column(name = "id")
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

    private String meta;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Integer Discount;

    @OneToMany(mappedBy = "product")
    private List<Cartitem> cartitems;



    public Product() {
    }
    public Product(String name, String slug, String image) {
        this.name = name;
        this.slug = slug;
        Image = image;
    }

    public Product(Integer id, String name, String slug, String image, String alt, String meta, Integer stock, double price, Integer discount, List<Cartitem> cartitems) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        Image = image;
        this.meta = meta;
        this.stock = stock;
        this.price = price;
        Discount = discount;
        this.cartitems = cartitems;
    }



    public Product(String name, String slug, String image, String alt, String meta, Integer stock, double price, Integer discount) {
        this.name = name;
        this.slug = slug;
        Image = image;
        this.meta = meta;
        this.stock = stock;
        this.price = price;
        Discount = discount;
    }

    public List<Cartitem> getCartitems() {
        return cartitems;
    }

    public void setCartitems(List<Cartitem> cartitems) {
        this.cartitems = cartitems;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return Discount;
    }

    public void setDiscount(Integer discount) {
        Discount = discount;
    }
}
