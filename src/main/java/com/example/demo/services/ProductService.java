package com.example.demo.services;

import com.example.demo.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product findProductById(long id);
    Product updateProduct(Product product);
    void deleteProductById(long id);


}
