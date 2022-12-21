package com.example.demo.controllers;

import com.example.demo.exception_handling.NoSuchProductException;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsRestController {

    private final ProductService productService;

    @GetMapping("/")
    @Operation(summary = "Возвращает список всех товаров")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if ((products == null) || (products.isEmpty())) {
            throw new NoSuchProductException("No product found in Database");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Возвращает товар по id")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Создает новый товар")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменяет товар с указанным id")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        if (productService.findProductById(id) == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        product.setId(id);
        productService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаляет товар с указанным id")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        if (productService.findProductById(id) == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
