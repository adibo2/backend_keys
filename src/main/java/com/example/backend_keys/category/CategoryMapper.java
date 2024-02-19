package com.example.backend_keys.category;

import com.example.backend_keys.cart.CartDto;
import com.example.backend_keys.cartitems.Cartitem;

import java.util.function.Function;
import java.util.stream.Collectors;

public class CategoryMapper implements Function<Category,CategoryDto> {
    public CategoryDto apply(Category category) {
        return new CategoryDto(category.getId(),
                category.getCategoryName()

        );
    }
}
