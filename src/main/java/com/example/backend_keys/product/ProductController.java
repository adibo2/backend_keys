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
    @GetMapping("/q/{query}")
    public ResponseEntity<ProductResponse> getProductsByQuery(@PathVariable String keyword,
                                                                @RequestParam(name = "pageNumber", defaultValue = Appconstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = Appconstant.PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy", defaultValue = Appconstant.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                                @RequestParam(name = "sortOrder", defaultValue = Appconstant.SORT_DIR, required = false) String sortOrder) {

        ProductResponse productResponse = productService.searchProductByKeyWord(keyword, pageNumber, pageSize, sortBy,
                sortOrder);

        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.FOUND);
    }


}
