package com.example.backend_keys.cart;

import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductDto;

import java.util.List;

public record CartDto(Integer cartId, double totalPrice, List<Product> products) {
}
