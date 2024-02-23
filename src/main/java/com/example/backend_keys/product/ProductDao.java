package com.example.backend_keys.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectProducts();

    Optional<Product> selectProductByname(String name);

    ProductDto insertProduct(Product product);

    boolean existProductName(String name);

    ProductDto updateProduct(Integer productId,Product product);

    ProductResponse searchProductByKeyWord(String query,Integer pageNumber,Integer pageSize,
                                           String sortBy,String sortOrder);


}
