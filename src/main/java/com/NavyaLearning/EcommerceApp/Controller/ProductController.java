package com.NavyaLearning.EcommerceApp.Controller;

import com.NavyaLearning.EcommerceApp.Model.Product;
import com.NavyaLearning.EcommerceApp.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins= "http://localhost:5173")
public class ProductController {

    private ProductService productService;
    //Constructor dependency injection
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> myProducts = productService.getAllProducts();
        return new ResponseEntity<>(myProducts, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id)
    {
        Product product = productService.getProductById(id);
        if(product != null)
        {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        byte[] imageFile = product.getImageData();

        //we have to send http code
        //the content type so that front end can handle it
        //and the actual image bytes
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile)
    {
        Product product1 = null;
        try {
            product1 = productService.updateProduct(id, product, imageFile);} catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(product1 != null)
        {
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("not updated : ( ", HttpStatus.BAD_REQUEST);
        }


    }


    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product product = productService.getProductById(id);
        if(product == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
    }

}
