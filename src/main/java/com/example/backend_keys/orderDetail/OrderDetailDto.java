package com.example.backend_keys.orderDetail;

import com.example.backend_keys.product.ProductDto;

public record OrderDetailDto(Integer orderDetailId, ProductDto productDto,
                             double discount,Integer quantity,double orderedProductPrice) {
}
