package com.example.backend_keys.product;

import java.util.function.Function;

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
