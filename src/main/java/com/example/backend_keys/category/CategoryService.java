package com.example.backend_keys.category;

import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Category> pageCategories = categoryRepo.findAll(pageDetails);
        List<Category> categories = pageCategories.getContent();
        if (categories.size() == 0) {
            throw new ApiException("No category ");

        }
        List<CategoryDto> categoryDtos=categories.stream()
                .map(el->categoryMapper.apply(el)).toList();

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setCategoryDtos(categoryDtos);
        categoryResponse.setTotalPages(pageCategories.getTotalPages());
        categoryResponse.setPageNumber(pageCategories.getNumber());
        categoryResponse.setPageNumber(pageNumber);
        categoryResponse.setPageSize(pageSize);
        categoryResponse.setLastPage(pageCategories.isLast());

        return categoryResponse;
    }
}
