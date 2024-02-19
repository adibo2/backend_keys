package com.example.backend_keys.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

    Category findByCategoryName(String CategoryName);

}
