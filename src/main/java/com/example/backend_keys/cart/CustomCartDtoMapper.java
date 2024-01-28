package com.example.backend_keys.cart;

import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomCartDtoMapper implements Function<Cart,CartDto> {

    public CartDto apply(Cart cart) {
        return new CartDto(cart.getId()
                ,cart.getTotalPrice(),
                cart.getCartitemList().stream().map(el->el.getProduct()).collect(Collectors.toList())
        );
    }
}
