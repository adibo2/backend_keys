package com.example.backend_keys.product;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.cart.CartRepository;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductDao {


    private final ProductRepisotory productRepisotory;
    private final CartRepository cartRepository;

    private final ProductDtoMapper productDtoMapper;
    public ProductService(
            ProductRepisotory productRepisotory, CartRepository cartRepository, ProductDtoMapper productDtoMapper){
        this.productRepisotory=productRepisotory;
        this.cartRepository = cartRepository;
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
        Product productfromDB= productRepisotory.findById(productId).orElseThrow(()->{
           return new RessourceNotFound("Product : " + product.getName() + " is Not found .");
        });
        if (productfromDB == null) {
            throw new ApiException("Product : " + product.getName() + " not here .");
        }
        product.setImage(productfromDB.getImage());

        double priceAfterdiscount = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());

        Product savedProduct = productRepisotory.save(product);




        return null;
    }
}
