package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.CartItemRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductDtoMapper;
import com.example.backend_keys.product.ProductRepisotory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepo cartItemRepo;
    @Mock
    private ProductRepisotory productRepisotory;
    @Mock
    private CustomCartDtoMapper customCartDtoMapper;

    private CartService underTest;


    private ProductDtoMapper productDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest =new CartService(cartRepository,cartItemRepo,productRepisotory,customCartDtoMapper,productDtoMapper);


    }



    @Test
    void itShouldDeleteProductFromCart() {
        // Given
        Integer cartId = 1;
        Integer productId = 1;
        double productPrice = 100.0;
        int quantity = 2;
        String productName = "Test Product";

        // Initial state of the cart
        double initialTotalPrice = 500.0; 
        //cart initaly 3ando 5 products
        int initialTotalProduct = 5; // Assume the cart initially has 5 products

        Cart cart = new Cart();
        cart.setTotalPrice(initialTotalPrice);
        cart.setTotalProduct(initialTotalProduct);

        Product product = new Product();
        product.setName(productName);

        Cartitem cartItem = new Cartitem();
        cartItem.setProductPrice(productPrice);
        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId)).thenReturn(cartItem);

        // When
        String result = underTest.deleteProductFromCart(cartId, productId);

        // Then
        assertEquals("Product " + productName + " removed from the cart !!!", result);
        verify(cartItemRepo).deleteCartItemByProductIdAndCartId(cartId, productId);

        // The total price and total product count should decrease by the amount related to the removed product
        assertEquals(initialTotalPrice - (productPrice * quantity), cart.getTotalPrice());
        assertEquals(initialTotalProduct - quantity, cart.getTotalProduct());
    }





    @Test
    void itShouldAddtoCart() {
        // Given
        Integer cartId = 1;
        Integer productId = 1;
        int quantityToAdd = 2;

        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setTotalPrice(0.0);
        cart.setTotalProduct(0);

        Product product = new Product();
        product.setId(productId);
        product.setPrice(50.0);
        product.setStock(10);
        product.setDiscount(0);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(productRepisotory.findById(productId)).thenReturn(Optional.of(product));

        // When
        Cart updatedCart = underTest.addtoCart(cartId, productId, quantityToAdd);

        // Then
        assertNotNull(updatedCart);
        assertEquals(100.0, updatedCart.getTotalPrice());
        assertEquals(2, updatedCart.getTotalProduct());

        ArgumentCaptor<Cartitem> cartItemArgumentCaptor = ArgumentCaptor.forClass(Cartitem.class);
        verify(cartItemRepo).save(cartItemArgumentCaptor.capture());
        Cartitem capturedCartItem = cartItemArgumentCaptor.getValue();

        assertEquals(productId, capturedCartItem.getProduct().getId());
        assertEquals(quantityToAdd, capturedCartItem.getQuantity());

    }


}