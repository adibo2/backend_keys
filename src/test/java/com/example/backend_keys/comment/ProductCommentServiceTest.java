package com.example.backend_keys.comment;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Date;

import static org.mockito.BDDMockito.then;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ProductCommentServiceTest {

    @Mock
    private ProductCommentRepo productCommentRepo;

    @Mock
    private ProductCommentDao productCommentDao;

    @Captor
    private ArgumentCaptor<ProductComment> productCommentArgumentCaptor;

    private ProductCommentService undertest;
    @BeforeEach
    void setUp() {
        //initialize mock
        MockitoAnnotations.initMocks(this);
        undertest=new ProductCommentService(productCommentRepo);
    }

    @Test
    void itShouldAddReviews() {
        //Given
        Product product=new Product("magic","url/adib","13332323");
        Customer customer=new Customer("amin","dssd@gmail.com",13);
        ProductCommentDto productCommentDto=new ProductCommentDto(10,product,"comment","product is good",5, new Date(),customer);

        //when
        undertest.addReviews(productCommentDto);
        //then
        then(productCommentRepo).should().save(productCommentArgumentCaptor.capture());
        ProductComment productComment=productCommentArgumentCaptor.getValue();


        assertThat(productComment.getProduct()).isEqualTo(product);
        assertThat(productComment).isEqualToComparingFieldByField(productCommentDto);
    }

    @Test
    void itShouldDeleteReviews() {
        //Given
        //when
        //then

    }
}