package com.example.multirubroproyectproducts.controllers;

import com.example.multirubroproyectproducts.models.Product;
import com.example.multirubroproyectproducts.requests.ProductRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.Implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.RecursiveTask;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/by-provider/{id}")
    public ResponseEntity<GenericResponse<List<Product>>> getProductsByProviderId(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductsByProvider(id));
    }
    @PostMapping
    public ResponseEntity<GenericResponse<Product>> addProduct(@RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<Product>>> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
