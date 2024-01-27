package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.CartItemRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartService implements CartDao{
    private CartRepo cartRepo;

    private CartItemRepo cartitemRepo;

    public CartService(CartRepo cartRepo, CartItemRepo cartitemRepo) {
        this.cartRepo = cartRepo;
        this.cartitemRepo = cartitemRepo;
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

    private double totalPrice(List<Cartitem> cartItems) {
        return cartItems.stream()
                .mapToDouble(el->el.getTotalPrice())
                .sum();
    }

    @Override
    public Cart addtoCart(Product product, int quantity, Customer customer) {
        Cart cart=customer.getCart();

        if(cart ==null){
            cart =new Cart();
        }

        List<Cartitem> cartitems=cart.getCartitemList();
        Cartitem cartitem=findCartitems(cartitems,product.getId());
        if(cartitems ==null){
            cartitems=new ArrayList<>();
            if (cartitem==null){
                cartitem=new Cartitem();
                cartitem.setProduct(product);
                cartitem.setTotalPrice(quantity * product.getPrice());
                cartitem.setQuantity(quantity);
                cartitem.setCart(cart);
                cartitems.add(cartitem);
                cartitemRepo.save(cartitem);
            }

        }else {
            if(cartitem ==null){
                cartitem=new Cartitem();
                cartitem.setProduct(product);
                cartitem.setTotalPrice(quantity * product.getPrice());
                cartitem.setQuantity(quantity);
                cartitem.setCart(cart);
                cartitems.add(cartitem);
                cartitemRepo.save(cartitem);
            }
            else{
                cartitem.setQuantity(cartitem.getQuantity()+quantity);
                cartitem.setTotalPrice(cartitem.getTotalPrice()+(cart.getTotalPrice())*quantity);
                cartitemRepo.save(cartitem);
            }

        }
        cart.setCartitemList(cartitems);
        int totalItems=totalItems(cart.getCartitemList());
        double totalPrice=totalPrice(cart.getCartitemList());
        cart.setTotalPrice(totalPrice);
        cart.setTotalProduct(totalItems);
        cart.setCustomer(customer);

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
        double totalPrice=totalPrice(cartitems);

        cart.setCartitemList(cartitems);
        cart.setTotalPrice(totalPrice);
        cart.setTotalProduct(totalitems);
        cartRepo.save(cart);



        return null;
    }
}
