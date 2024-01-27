package com.example.backend_keys.comment;

public interface ProductCommentDao {
    ProductComment addReviews(ProductCommentDto productCommentDto);
    void deleteReviews(Integer id);

    ProductComment updateReviews(ProductCommentDto productCommentDto);
}
