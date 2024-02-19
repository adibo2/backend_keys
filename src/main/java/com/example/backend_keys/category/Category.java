package com.example.backend_keys.category;

import com.example.backend_keys.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Table
@Entity(name = "category")
public class Category {
    @Id
    //it like auto increment
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_id_sequence"
    )
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(min = 5, message = "Category name must contain atleast 5 characters")
    @Column()
    private String categoryName;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
