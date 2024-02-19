package com.example.backend_keys.category;

public interface CategortDao {

    CategoryDto createCategory(Category category);
    CategoryDto updateCategory(Category category, Integer categoryId);

    String deleteCategory(Integer categoryId);

}
