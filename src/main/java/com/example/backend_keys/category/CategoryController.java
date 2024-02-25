package com.example.backend_keys.category;

import com.example.backend_keys.product.Appconstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getcategories")
    public ResponseEntity<CategoryResponse> getCategories(
            @RequestParam(name = "pageNumber", defaultValue = Appconstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = Appconstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = Appconstant.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = Appconstant.SORT_DIR, required = false) String sortOrder) {

        CategoryResponse categoryResponse = categoryService.getCategories(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.FOUND);
    }

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody Category category) {
        CategoryDto savedCategoryDTO = categoryService.createCategory(category);

        return new ResponseEntity<CategoryDto>(savedCategoryDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        String status = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
