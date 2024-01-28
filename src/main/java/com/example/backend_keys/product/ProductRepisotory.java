package com.example.backend_keys.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepisotory extends JpaRepository<Product,Integer> {

    Optional<Product> findProductByName(String name);

    @Query(value = "select p from product p where p.name like %?1% ",nativeQuery = true)
    List<Product> findAllByNameOrDescription(String search);

    @Query(value = "select p from product p order by p.cost_price asc limit 9",
            nativeQuery = true)
    List<Product> filterLowerProducts();
}
