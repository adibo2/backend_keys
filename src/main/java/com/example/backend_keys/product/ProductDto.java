package com.example.backend_keys.product;

public record ProductDto(Integer id,String name,String slug,
                         String Image,double price,double Discount, Integer stock ) {
}
