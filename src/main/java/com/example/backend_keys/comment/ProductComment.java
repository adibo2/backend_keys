package com.example.backend_keys.comment;


import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "productComment")
@Entity(name = "productComment")
public class ProductComment {
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

    @ManyToOne
    @JoinColumn(name = "product_id"
            ,referencedColumnName ="id",
            foreignKey = @ForeignKey(
            name = "product_comment_fk"
    ))
    private Product product;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "rating")
    private int rating;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id"
            ,referencedColumnName = "id", foreignKey = @ForeignKey(
            name = "customer_comment_fk"))
    private Customer customer;

    public ProductComment() {
    }
}
