package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.product.ProductDtoMapper;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomCartDtoMapper implements Function<Cart,CartDto> {

    private final ProductDtoMapper productDtoMapper;

    public CustomCartDtoMapper(ProductDtoMapper productDtoMapper) {
        this.productDtoMapper = productDtoMapper;
    }

    public CartDto apply(Cart cart) {
        return new CartDto(cart.getId()
                ,cart.getTotalPrice(),
                cart.getCartitemList().stream()
                        .map(Cartitem::getProduct)
                        .map(el->productDtoMapper.apply(el)).
                        collect(Collectors.toList())
        );
    }
}
