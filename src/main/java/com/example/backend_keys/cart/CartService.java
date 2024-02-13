package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.CartItemRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductRepisotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements CartDao{

    private final CartRepository cartRepository;
    private final CustomCartDtoMapper customCartDtoMapper;

    private final CartItemRepo cartitemRepo;

    private final ProductRepisotory productRepisotory;


    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepo cartitemRepo,
                       ProductRepisotory productRepisotory, CustomCartDtoMapper customCartDtoMapper) {
        this.cartRepository = cartRepository;
        this.cartitemRepo = cartitemRepo;
        this.productRepisotory=productRepisotory;
        this.customCartDtoMapper=customCartDtoMapper;
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
        Cart cart= cartRepository.findById(cartId)
                .orElseThrow(()->new RessourceNotFound("customer with id %s".formatted(cartId)));

        Product product=productRepisotory.findById(productId)
                .orElseThrow(()->new RessourceNotFound("customer with id %s".formatted(productId)));
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

        return cartRepository.save(cart);
    }


    @Override
    public CartDto getCart(Integer id) {
        return cartRepository.findCartById(id).map(customCartDtoMapper)
                .orElseThrow(() -> new RessourceNotFound(
                        "cart with id [%s] not found".formatted(id)
                ));
    }

    @Override
    public void updateProductInCarts(Integer cartId, Integer productId,int quantity) {
        Cart cart= cartRepository.findById(cartId).orElseThrow(()->(
                new RessourceNotFound( "cart with id [%s] not found".formatted(cartId))
                ));
        Product product=productRepisotory.findById(productId)
                .orElseThrow(()->new RessourceNotFound("customer with id %s".formatted(productId)));

        Cartitem cartitem=cartitemRepo.findCartItemByProductIdAndCartId(cartId,productId);

        if(cartitem == null){
            throw new ApiException("Product " + product.getName() + " not available in the cart!!!");
        }
        cartitem.setQuantity(quantity);
        cart.setTotalPrice(cart.getTotalPrice() + (cart.getTotalPrice() * quantity));

    }



    @Override
    public String deleteProductFromCart(Integer cartId, Integer productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RessourceNotFound("customer with email %s".formatted(productId)));

        Cartitem cartItem = cartitemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new RessourceNotFound("customer with email %s".formatted(productId));

        }

        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));
        cart.setTotalProduct(cart.getTotalProduct() - cartItem.getQuantity());

        Product product = cartItem.getProduct();
        cartitemRepo.deleteCartItemByProductIdAndCartId(cartId,productId);
        return "Product " + cartItem.getProduct().getName() + " removed from the cart !!!";

    }
}
