package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.CartItemRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductRepisotory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CartServiceTest {

    @Mock
    private CartRepo cartRepo;

    @Mock
    private CartItemRepo cartItemRepo;
    @Mock
    private ProductRepisotory productRepisotory;
    @Mock
    private CustomCartDtoMapper customCartDtoMapper;

    private CartService underTest;


    @BeforeEach
    void setUp() {
        underTest =new CartService(cartRepo,cartItemRepo,productRepisotory,customCartDtoMapper);


    }

    @Test
    void itShouldDeleteItemFromCart() {
        // Given
        Integer cartId = 1;
        Integer productId = 1;
        double productPrice = 100.0;
        int quantity = 2;
        double initialTotalPrice = 200.0;
        int initialTotalProduct = 2;
        int initialStock = 5;

        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setTotalPrice(initialTotalPrice);
        cart.setTotalProduct(initialTotalProduct);

        Product product = new Product();
        product.setName("Test Product");
        product.setStock(initialStock);

        Cartitem cartItem = new Cartitem();
        cartItem.setProductPrice(productPrice);
        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);

        when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));

        when(cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId)).thenReturn(cartItem);

        // When
        String response = underTest.deleteProductFromCart(cartId, productId);

        // Then
        String expectedMessage = "Product " + product.getName() + " removed from the cart !!!";
        assertEquals(expectedMessage, response);
        verify(cartItemRepo).deleteCartItemByProductIdAndCartId(cartId, productId);
        assertEquals(initialTotalPrice - (productPrice * quantity), cart.getTotalPrice());
        assertEquals(initialTotalProduct - quantity, cart.getTotalProduct());
        assertEquals(initialStock + quantity, product.getStock());
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

        when(cartRepo.findById(cartId)).thenReturn(Optional.of(cart));
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