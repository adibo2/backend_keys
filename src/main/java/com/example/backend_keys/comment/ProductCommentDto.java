package com.example.backend_keys.comment;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;

import java.util.Date;

public record ProductCommentDto( Integer id, Product product,
                                String title, String body, int rating,
                                Date createDate, Customer customer) {
}
