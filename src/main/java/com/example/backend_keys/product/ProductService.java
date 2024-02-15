package com.example.backend_keys.product;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerDao;
import com.example.backend_keys.customer.CustomerRegistrationRequest;
import com.example.backend_keys.exception.DuplicateRessource;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.order.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductDao {


    private final ProductRepisotory productRepisotory;

    private final ProductDtoMapper productDtoMapper;
    public ProductService(
                          ProductRepisotory productRepisotory,ProductDtoMapper productDtoMapper){
        this.productRepisotory=productRepisotory;
        this.productDtoMapper=productDtoMapper;
    }
/*
    public List<Product> getAllProducts(){
        return productDao.selectProducts();
    }

    public Product getProductName(String name){
        return productDao.selectProductByname(name)
                .orElseThrow(()->
                        new RessourceNotFound("product with name %s".formatted(name)));

    }*/

    public ProductDto insertProduct(Product product){
        product.setImage("dsd");
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        Product savedProduct = productRepisotory.save(product);
        ProductDto productDto=productDtoMapper.apply(savedProduct);

        return productDto;

    }

    @Override
    public List<Product> selectProducts() {
        return null;
    }

    @Override
    public Optional<Product> selectProductByname(String name) {
        return Optional.empty();
    }



    @Override
    public boolean existProductName(String name) {
        return false;
    }

    @Override
    public ProductDto updateProduct(Integer productId, Product product) {
        return null;
    }
}
