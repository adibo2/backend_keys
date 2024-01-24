package com.example.backend_keys.product;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerDao;
import com.example.backend_keys.customer.CustomerRegistrationRequest;
import com.example.backend_keys.exception.DuplicateRessource;
import com.example.backend_keys.exception.RessourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;
    public ProductService(@Qualifier("Product") ProductDao productDao){
        this.productDao=productDao;
    }

    public List<Product> getAllProducts(){
        return productDao.selectProducts();
    }

    public Product getProductName(String name){
        return productDao.selectProductByname(name)
                .orElseThrow(()->
                        new RessourceNotFound("product with name %s".formatted(name)));

    }

    public void insertProduct(ProductRegistrationRequest productRegistrationRequest){
        String name=productRegistrationRequest.name();
        if(productDao.existProductName(name)){
            throw new DuplicateRessource("product with name %s already exist".formatted(name));
        }
        productDao.insertProduct(new Product(
                productRegistrationRequest.name(),
                productRegistrationRequest.slug(),
                productRegistrationRequest.Image(),
                productRegistrationRequest.alt(),
                productRegistrationRequest.meta(),
                productRegistrationRequest.stock(),
                productRegistrationRequest.price(),
                productRegistrationRequest.Discount()
        ));
    }

}
