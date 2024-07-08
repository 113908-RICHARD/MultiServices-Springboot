package com.example.multirubroproyectproducts.services.Implementation;

import com.example.multirubroproyectproducts.entities.CategoryEntity;
import com.example.multirubroproyectproducts.entities.ProductEntity;
import com.example.multirubroproyectproducts.entities.ProviderEntity;
import com.example.multirubroproyectproducts.models.Category;
import com.example.multirubroproyectproducts.models.Product;
import com.example.multirubroproyectproducts.repositories.CategoryRepository;
import com.example.multirubroproyectproducts.repositories.ProductRepository;
import com.example.multirubroproyectproducts.repositories.ProviderRepository;
import com.example.multirubroproyectproducts.requests.ProductRequest;
import com.example.multirubroproyectproducts.requests.UpdateProductRequest;
import com.example.multirubroproyectproducts.requests.UpdateProductStockRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.ICategoryService;
import com.example.multirubroproyectproducts.services.IProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public GenericResponse<List<Product>> getAllProducts() {
        GenericResponse<List<Product>> response = new GenericResponse<>();
        List<ProductEntity> productsEntities = productRepository.findAll();
        if (productsEntities.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("No products found");
            return response;

        }
        response.setStatus(HttpStatus.OK);
        response.setMessage("Found " + productsEntities.size() + " products");

        List<Product> productsList = new ArrayList<>();
        for (ProductEntity productEntity : productsEntities) {
            productsList.add(modelMapper.map(productEntity, Product.class));
        }
        response.setData(productsList);
        return response;
    }

    @Override
    public GenericResponse<Product> getProductById(UUID id) {
        GenericResponse<Product> response = new GenericResponse<>();
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if (productEntityOptional.isPresent()) {
            response.setStatus(HttpStatus.OK);
            response.setMessage("product found");
            response.setData(modelMapper.map(productEntityOptional.get(), Product.class));
            return response;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No product found with id " + id);
    }

    @Override
    @Transactional
    public GenericResponse<Product> createProduct(ProductRequest product) {
        GenericResponse<Product> response = new GenericResponse<>();

        List<ProviderEntity> providerEntities = getProviderEntities(product.getProviders());
        List<CategoryEntity> categoryEntities = getCategoryEntities(product.getCategories());

        ProductEntity productEntity = getProductEntity(product, providerEntities, categoryEntities);
        try {
            productRepository.save(productEntity);
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Product created");
            response.setData(modelMapper.map(productEntity, Product.class));
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
        return response;

    }

    protected List<ProviderEntity> getProviderEntities(List<UUID> providerIds) {
        List<ProviderEntity> providerEntities = new ArrayList<>();
        for (UUID providerId : providerIds) {
            providerEntities.add(providerRepository.findById(providerId).get());
        }
        return providerEntities;
    }

    protected List<CategoryEntity> getCategoryEntities(List<UUID> categoriesIds) {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        for (UUID categoryId : categoriesIds) {
            categoryEntities.add(categoryRepository.findById(categoryId).get());
        }
        return categoryEntities;
    }
    protected ProductEntity getProductEntity(ProductRequest productRequest, List<ProviderEntity> providerEntities,List<CategoryEntity> categories) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setStock(productRequest.getStock());
        productEntity.setProviders(providerEntities);
        productEntity.setCategories(categories);
        return  productEntity;
    }

    @Override
    public GenericResponse<Product> updateProduct(UpdateProductRequest product) {
        return null;
    }

    @Override
    public GenericResponse<Product> updateProductStock(UpdateProductStockRequest request) {
        return null;
    }

    @Override
    public GenericResponse<List<Product>> getProductsByProvider(UUID id) {
        return null;
    }

    @Override
    public GenericResponse<List<Product>> getProductsByCategories(List<Category> categories) {
        return null;
    }

    @Override
    public GenericResponse<List<Product>> getProductsByCategoriesAndProviderId(List<Category> categories, UUID providerId) {
        return null;
    }
}