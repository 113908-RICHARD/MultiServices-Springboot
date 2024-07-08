package com.example.multirubroproyectproducts.services;

import com.example.multirubroproyectproducts.models.Category;
import com.example.multirubroproyectproducts.models.Product;
import com.example.multirubroproyectproducts.requests.ProductRequest;
import com.example.multirubroproyectproducts.requests.UpdateProductRequest;
import com.example.multirubroproyectproducts.requests.UpdateProductStockRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IProductService {

    GenericResponse<List<Product>> getAllProducts();
    GenericResponse<Product> getProductById(UUID id);
    GenericResponse<Product> createProduct(ProductRequest product);
    GenericResponse<Product> updateProduct(UpdateProductRequest product);
    GenericResponse<Product> updateProductStock(UpdateProductStockRequest request);
    GenericResponse<List<Product>> getProductsByProvider(UUID id);
    GenericResponse<List<Product>> getProductsByCategories(List<Category> categories);
    GenericResponse<List<Product>> getProductsByCategoriesAndProviderId(List<Category> categories, UUID providerId);
}
