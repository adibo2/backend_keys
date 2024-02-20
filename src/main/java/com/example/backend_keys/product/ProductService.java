package com.example.backend_keys.product;

import com.example.backend_keys.cart.*;
import com.example.backend_keys.exception.ApiException;
import com.example.backend_keys.exception.RessourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductDao {


    private final ProductRepisotory productRepisotory;
    private final CartRepository cartRepository;

    private final ProductDtoMapper productDtoMapper;

    private final CustomCartDtoMapper customCartDtoMapper;

    private final CartService cartService;

    public ProductService(
            ProductRepisotory productRepisotory, CartRepository cartRepository, ProductDtoMapper productDtoMapper, CustomCartDtoMapper customCartDtoMapper, CartService cartService){
        this.productRepisotory=productRepisotory;
        this.cartRepository = cartRepository;
        this.productDtoMapper=productDtoMapper;
        this.customCartDtoMapper = customCartDtoMapper;
        this.cartService = cartService;
    }
/*
    public List<Product> getAllProducts(){
        return productDao.selectProducts();
    }

    public Product getProductName(String name){
        return productDao.selectProductByname(name)
                .orElseThrow(()->
                        new RessourceNotFound("product with name %s".formatted(name)));

    }*/

    public ProductDto insertProduct(Product product){
        product.setImage("dsd");
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        Product savedProduct = productRepisotory.save(product);
        ProductDto productDto=productDtoMapper.apply(savedProduct);

        return productDto;

    }

    @Override
    public List<Product> selectProducts() {
        return null;
    }

    @Override
    public Optional<Product> selectProductByname(String name) {
        return Optional.empty();
    }



    @Override
    public boolean existProductName(String name) {
        return false;
    }

    @Override
    public ProductDto updateProduct(Integer productId, Product product) {
        Product productfromDB= productRepisotory.findById(productId).orElseThrow(()->{
           return new RessourceNotFound("Product : " + product.getName() + " is Not found .");
        });
        if (productfromDB == null) {
            throw new ApiException("Product : " + product.getName() + " not here .");
        }
        product.setImage(productfromDB.getImage());

        double priceAfterdiscount = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());

        Product savedProduct = productRepisotory.save(product);

        List<Cart> carts=cartRepository.findCartByProductId(productId);
        List<CartDto> cartDtos=carts.stream()
                .map(cart -> {
                    CartDto cartDto=customCartDtoMapper.apply(cart);
                    List<ProductDto> productDtos=cart.getCartitemList().stream()
                            .map(pr->pr.getProduct())
                            .map(el->productDtoMapper.apply(el)).toList();
                    CartDto updateCartDto = new CartDto(cartDto.cartId(), cartDto.totalPrice(), productDtos);
                    return updateCartDto;

                }).toList();
        cartDtos.forEach(cart->cartService.updateProductsInCart(cart.cartId(),productId));



        return productDtoMapper.apply(savedProduct);
    }
}
