package com.example.backend_keys.product;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("Product")
public class ProductJPADataAccessService implements ProductDao{

    private final ProductRepisotory productRepisotory;

    public ProductJPADataAccessService(ProductRepisotory productRepisotory) {
        this.productRepisotory = productRepisotory;
    }

    @Override
    public List<Product> selectProducts() {
        return productRepisotory.findAll();
    }

    @Override
    public Optional<Product> selectProductByname(String name) {
        return productRepisotory.findProductByName(name);
    }

    @Override
    public void insertProduct(Product product) {
         productRepisotory.save(product) ;

    }

    @Override
    public boolean existProductName(String name) {
        return false;
    }
}
