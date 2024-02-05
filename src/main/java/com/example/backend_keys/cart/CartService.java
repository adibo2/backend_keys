package com.example.backend_keys.cart;

import com.example.backend_keys.cartitems.CartItemRepo;
import com.example.backend_keys.cartitems.Cartitem;
import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import com.example.backend_keys.product.Product;
import com.example.backend_keys.product.ProductRepisotory;

import java.util.List;
import java.util.stream.Collectors;

public class CartService implements CartDao{
    private CartRepo cartRepo;
    private final CustomCartDtoMapper customCartDtoMapper;

    private CartItemRepo cartitemRepo;

    private ProductRepisotory productRepisotory;

    public CartService(CartRepo cartRepo, CartItemRepo cartitemRepo,ProductRepisotory productRepisotory,CustomCartDtoMapper customCartDtoMapper) {
        this.cartRepo = cartRepo;
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
        Cart cart=cartRepo.findById(cartId)
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

    @Override
    public CartDto getCart(Integer id) {
        return cartRepo.findCartById(id).map(customCartDtoMapper)
                .orElseThrow(() -> new RessourceNotFound(
                        "cart with id [%s] not found".formatted(id)
                ));
    }

    @Override
    public void updateProductInCarts(Integer cartId, Integer productId,int quantity) {
        Cart cart=cartRepo.findById(cartId).orElseThrow(()->(
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
    public CartDTO updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        if (product.getQuantity() == 0) {
            throw new APIException(product.getProductName() + " is not available");
        }

        if (product.getQuantity() < quantity) {
            throw new APIException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getQuantity() + ".");
        }

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new APIException("Product " + product.getProductName() + " not available in the cart!!!");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());

        product.setQuantity(product.getQuantity() + cartItem.getQuantity() - quantity);

        cartItem.setProductPrice(product.getSpecialPrice());
        cartItem.setQuantity(quantity);
        cartItem.setDiscount(product.getDiscount());

        cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * quantity));

        cartItem = cartItemRepo.save(cartItem);

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<ProductDTO> productDTOs = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), ProductDTO.class)).collect(Collectors.toList());

        cartDTO.setProducts(productDTOs);

        return cartDTO;

    }

    @Override
    public String deleteProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        CartItem cartItem = cartItemRepo.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ResourceNotFoundException("Product", "productId", productId);
        }

        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));

        Product product = cartItem.getProduct();
        product.setQuantity(product.getQuantity() + cartItem.getQuantity());

        cartItemRepo.deleteCartItemByProductIdAndCartId(cartId, productId);

        return "Product " + cartItem.getProduct().getProductName() + " removed from the cart !!!";
    }
}
