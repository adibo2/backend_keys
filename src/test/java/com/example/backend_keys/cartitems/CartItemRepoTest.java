package com.example.backend_keys.cartitems;

import com.example.backend_keys.cart.Cart;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CartItemRepoTest {

    @Autowired
    private CartItemRepo underTest;



    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
        System.out.println(applicationContext.getBeanDefinitionCount());

    }

    @Test
    void itShouldFindCartItemByProductIdAndCartId() {
        //Given
        //...  Setting up a Cart
        Cart cart=new Cart();
        cart.setTotalProduct(5); // Example property
        cart.setTotalPrice(100.00);

        Customer customer=new Customer("ahmed","aeaaa@gmail.com",12);
        cart.setCustomer(customer);
        cart=entityManager.persistFlushFind(cart);

        //...setting a product
        Product product=new Product("tide","/aza","23232");
        product.setStock(10); // Example property, set other non-nullable fields as well
        product.setPrice(20.00); // Example property
        product.setDiscount(0);
        product.setMeta("buy product");
        product=entityManager.persistFlushFind(product);

        //...setting a cartItem
        Cartitem cartitem=new Cartitem(10.00,2,23.7,cart,product);
        cartitem=entityManager.persistFlushFind(cartitem);

        //when
        Cartitem found=underTest.findCartItemByProductIdAndCartId(cart.getId(), product.getId());
        //then
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(cartitem.getId());

    }

    @Test
    void itShouldDeleteCartItemByProductIdAndCartId() {
        //Given
        //...  Setting up a Cart
        Cart cart=new Cart();
        cart.setTotalProduct(5); // Example property
        cart.setTotalPrice(100.00);

        Customer customer=new Customer("ahmed","aeaaa@gmail.com",12);
        cart.setCustomer(customer);
        cart=entityManager.persistFlushFind(cart);

        //...setting a product
        Product product=new Product("tide","/aza","23232");
        product.setStock(10); // Example property, set other non-nullable fields as well
        product.setPrice(20.00); // Example property
        product.setDiscount(0);
        product.setMeta("buy product");
        product=entityManager.persistFlushFind(product);

        //...setting a cartItem
        Cartitem cartitem=new Cartitem(10.00,2,23.7,cart,product);
        cartitem=entityManager.persistFlushFind(cartitem);

        //when
        underTest.deleteCartItemByProductIdAndCartId(cart.getId(),product.getId());
        entityManager.clear();
        //then
        Cartitem foundCartItem=entityManager.find(Cartitem.class,cartitem.getId());
        assertThat(foundCartItem).isNull();

    }
}