package com.example.demo.services;

import com.example.demo.models.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);
    List<Product> getAllProducts();
    Product findProductById(long id);
    void updateProduct(Product product);
    void deleteProductById(long id);
}
