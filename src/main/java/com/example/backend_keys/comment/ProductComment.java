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

    public ProductComment( Product product, String title, String body, int rating, Date createDate, Customer customer) {
        this.product = product;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.createDate = createDate;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "id=" + id +
                ", product=" + product +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", rating=" + rating +
                ", createDate=" + createDate +
                ", customer=" + customer +
                '}';
    }
}
