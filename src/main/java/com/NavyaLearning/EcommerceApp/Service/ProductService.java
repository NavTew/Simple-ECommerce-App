package com.NavyaLearning.EcommerceApp.Service;

import com.NavyaLearning.EcommerceApp.Model.Product;
import com.NavyaLearning.EcommerceApp.Repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
