package com.example.backend_keys.cart;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")

public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/carts/{cartId}/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDto> addtoCart(@PathVariable Integer cartId, @PathVariable Integer productId, @PathVariable Integer quantity) {
        CartDto cartDTO = cartService.addtoCart(cartId, productId, quantity);

        return new ResponseEntity<CartDto>(cartDTO, HttpStatus.CREATED);
    }

    @GetMapping("/users/{emailId}/carts/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable String emailId, @PathVariable Integer cartId) {
        CartDto cartDTO = cartService.getCart(emailId, cartId);

        return new ResponseEntity<CartDto>(cartDTO, HttpStatus.FOUND);
    }

    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
        String status = cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
