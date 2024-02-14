package com.example.backend_keys.product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectProducts();

    Optional<Product> selectProductByname(String name);

    void insertProduct(Product product);

    boolean existProductName(String name);

    ProductDto updateProduct(Integer productId,Product product);


}
