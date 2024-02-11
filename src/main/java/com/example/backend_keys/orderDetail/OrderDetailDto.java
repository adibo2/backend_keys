package com.example.backend_keys.orderDetail;

import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductDto;

public record OrderDetailDto(Integer orderDetailId, Product product,
                             double discount,Integer quantity,double orderedProductPrice) {
}
