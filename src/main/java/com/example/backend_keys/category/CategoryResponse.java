package com.example.backend_keys.category;

import com.example.backend_keys.product.ProductDto;

import java.util.List;

public class CategoryResponse {

    private List<CategoryDto> categoryDtos;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElements;
    private Integer totalPages;
    private boolean lastPage;

    public CategoryResponse() {
    }

    public CategoryResponse(List<CategoryDto> categoryDtos, Integer pageNumber, Integer pageSize, Integer totalElements, Integer totalPages, boolean lastPage) {
        this.categoryDtos = categoryDtos;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }

    public List<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
