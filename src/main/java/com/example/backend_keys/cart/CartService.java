package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.CartItemRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductRepisotory;

import java.util.List;

public class CartService implements CartDao{
    private CartRepo cartRepo;

    private CartItemRepo cartitemRepo;

    private ProductRepisotory productRepisotory

    public CartService(CartRepo cartRepo, CartItemRepo cartitemRepo,ProductRepisotory productRepisotory) {
        this.cartRepo = cartRepo;
        this.cartitemRepo = cartitemRepo;
        this.productRepisotory=productRepisotory;
    }

    private Cartitem findCartitems(List<Cartitem> cartitems, Integer productId){
        if(cartitems ==null){
            return null;
        }
        return cartitems.stream().filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    private int totalItems(List<Cartitem> cartItems) {
        return cartItems.stream()
                .mapToInt(el-> el.getQuantity())
                .sum();
    }

   /* private double totalPrice(List<Cartitem> cartItems) {
        return cartItems.stream()
                .mapToDouble(el->el.getTotalPrice())
                .sum();
    }*/

    @Override
    public Cart addtoCart(Integer cartId, Integer productId, int quantity) {
        Cart cart=cartRepo.findById(cartId)
                .orElseThrow(()->new RessourceNotFound("customer with id %s".formatted(cartId)));

        Product product=productRepisotory.findById(productId)
                .orElseThrow(()->new RessourceNotFound("customer with id %s".formatted(cartId)));
        Cartitem cartitem=cartitemRepo.findCartItemByProductIdAndCartId(cartId,productId);


        if (product.getStock() == 0) {
            throw new ApiException(product.getName() + " is not available");
        }

        if (product.getStock() < quantity) {
            throw new ApiException("Product : " + product.getName()
                    + " is out of Stock .");
        }

        List<Cartitem> cartItems = cart.getCartitemList();


        if(cartitem !=null){
            cartitem.setQuantity(cartitem.getQuantity()+quantity);
            cartitemRepo.save(cartitem);
        }else{
            Cartitem newcartitem =new Cartitem();
            newcartitem.setProduct(product);
            newcartitem.setQuantity(quantity);
            newcartitem.setCart(cart);
            newcartitem.setDiscount(product.getDiscount());
            newcartitem.setProductPrice(product.getPrice());
            cartItems.add(newcartitem);
            cartitemRepo.save(newcartitem);
        }
        cart.setCartitemList(cartItems);
        int totalitems=totalItems(cart.getCartitemList());
        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));
        cart.setTotalProduct(totalitems);

        return cartRepo.save(cart);
    }

    @Override
    public Cart deleteItemFromCart(Product product, Customer customer) {
        Cart cart = customer.getCart();
        List<Cartitem> cartitems=cart.getCartitemList();
        Cartitem item=findCartitems(cartitems,product.getId());
        cartitems.remove(item);
        cartitemRepo.delete(item);
        int totalitems=totalItems(cartitems);
        //double totalPrice=totalPrice(cartitems);

        cart.setCartitemList(cartitems);
        //cart.setTotalPrice(totalPrice);
        cart.setTotalProduct(totalitems);
        cartRepo.save(cart);



        return null;
    }
}
