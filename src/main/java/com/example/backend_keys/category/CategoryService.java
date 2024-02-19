package com.example.backend_keys.category;

import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.product.Product;

import java.util.List;

public class CategoryService implements CategortDao {

    private CategoryRepo categoryRepo;
    private CategoryMapper categoryMapper;

    public CategoryService(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto createCategory(Category category) {
        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());

        if (savedCategory != null) {
            throw new ApiException("Category with the name '" + category.getCategoryName() + "' already exists !!!");
        }

        savedCategory = categoryRepo.save(category);

        return categoryMapper.apply(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(Category category, Integer categoryId) {
        return null;
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RessourceNotFound(("Category with id %s".formatted(categoryId))));

        List<Product> products = category.getProducts();



        categoryRepo.delete(category);

        return "Category with categoryId: " + categoryId + " deleted successfully !!!";
    }
}
