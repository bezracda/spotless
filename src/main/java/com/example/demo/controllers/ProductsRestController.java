package com.example.demo.controllers;

import com.example.demo.exception_handling.IncorrectProductData;
import com.example.demo.exception_handling.NoSuchProductException;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Все методы для работы с товарами")
public class ProductsRestController {

    private final ProductService productService;

    @GetMapping("/")
    @Operation(summary = "Возвращает список всех товаров")
    @ApiResponse(
            responseCode = "200",
            description = "Товары успешно найдены",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Product.class))
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Нет ни одного товара в базе данных",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = IncorrectProductData.class)
            ))
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if ((products == null) || (products.isEmpty())) {
            throw new NoSuchProductException("No product found in Database");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Возвращает товар по идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Товар с таким идентификатором найден",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Товара с таким идентификатором не существует",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = IncorrectProductData.class)
            ))
    public ResponseEntity<Product> getProduct(@PathVariable @Parameter(description = "Идентификатор товара") Long id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/")
    @Operation(summary = "Создает новый товар")
    @ApiResponse(
            responseCode = "201",
            description = "Товар успешно создан",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Товар с таким идентификатором уже существует",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = IncorrectProductData.class)
            ))
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (productService.findProductById(product.getId()) != null) {
            throw new NoSuchProductException("Product with this ID = " + product.getId() + " already exist");
        }
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменяет товар с указанным идентификатором")
    @ApiResponse(
            responseCode = "200",
            description = "Товар успешно изменен",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Товара с таким идентификатором не существует",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = IncorrectProductData.class)
            ))
    public ResponseEntity<Product> updateProduct(
            @RequestBody Product product,
            @Parameter(description = "Идентификатор товара") @PathVariable Long id) {
        if (productService.findProductById(id) == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        product.setId(id);
        productService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаляет товар с указанным идентификатором")
    @ApiResponse(
            responseCode = "204",
            description = "Товар успешно удален",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)
            ))
    @ApiResponse(
            responseCode = "404",
            description = "Товара с таким идентификатором не существует",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = IncorrectProductData.class)
            ))
    public ResponseEntity<Product> deleteProduct(
            @Parameter(description = "Идентификатор товара") @PathVariable Long id) {
        if (productService.findProductById(id) == null) {
            throw new NoSuchProductException("There is no product with ID = " + id + " in Database");
        }
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
