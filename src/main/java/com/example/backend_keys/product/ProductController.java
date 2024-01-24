package com.example.backend_keys.product;


import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProucts(){
        return productService.getAllProducts();
    }

    @GetMapping("{name}")
    public Product getProduct(@PathVariable("name") String name){
        return productService.getProductName(name);

    }
    @PostMapping
    public void addProduct(@RequestBody ProductRegistrationRequest request){
        productService.insertProduct(request);
    }

}
