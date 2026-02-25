package com.NavyaLearning.EcommerceApp.Controller;

import com.NavyaLearning.EcommerceApp.Model.Product;
import com.NavyaLearning.EcommerceApp.Service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;
    //Constructor dependency injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String greet() {
        return "hello World";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
