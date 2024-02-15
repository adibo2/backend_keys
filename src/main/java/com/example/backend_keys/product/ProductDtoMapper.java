package com.example.backend_keys.product;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDtoMapper implements Function<Product,ProductDto> {
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getImage(),
                product.getPrice(),
                product.getDiscount(),
                product.getStock()
        );
    }
}
