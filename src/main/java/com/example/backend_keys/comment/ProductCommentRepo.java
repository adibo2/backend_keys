package com.example.backend_keys.comment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("productComment")
public interface ProductCommentRepo extends JpaRepository<ProductComment,Integer> {
    @Query("select p from productComment p  WHERE p.id= ?1 ")
    Optional<ProductComment> getCommentsByProduct(Integer id);
}
