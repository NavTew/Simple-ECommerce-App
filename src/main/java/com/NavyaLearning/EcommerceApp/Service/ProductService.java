package com.NavyaLearning.EcommerceApp.Service;

import com.NavyaLearning.EcommerceApp.Model.Product;
import com.NavyaLearning.EcommerceApp.Repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product getProductById(int id)
    {
        return productRepo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {

        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setImageData(imageFile.getBytes());
        existing.setImageName(imageFile.getOriginalFilename());
        existing.setImageType(imageFile.getContentType());

        return productRepo.save(existing);
    }



/*
    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        return productRepo.save(product);

    }
    It’s working like update because of how productRepo.save(product)
    works in Spring Boot (actually Spring Data JPA).

            🔹 How save() decides: Insert vs Update

              save() checks the primary key (id) of the entity.

      ✅ If id == null → INSERT

      ✅ If id != null and exists in DB → UPDATE

*/

    public void deleteProduct(int id) {

        productRepo.deleteById(id);

    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
