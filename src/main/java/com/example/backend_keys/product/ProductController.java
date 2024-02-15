package com.example.backend_keys.product;


import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private final ProductService productService;



    public ProductController(ProductService productService) {
        this.productService = productService;
    }

  /*  @GetMapping
    public List<Product> getProucts(){
        return productService.getAllProducts();
    }

    @GetMapping("{name}")
    public Product getProduct(@PathVariable("name") String name){
        return productService.getProductName(name);

    }*/
    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product product){
        ProductDto savedProduct = productService.insertProduct(product);
        return new ResponseEntity<ProductDto>(savedProduct, HttpStatus.CREATED);

    }

}
