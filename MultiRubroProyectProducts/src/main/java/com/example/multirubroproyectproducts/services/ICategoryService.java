package com.example.multirubroproyectproducts.services;

import com.example.multirubroproyectproducts.models.Category;
import com.example.multirubroproyectproducts.requests.CategoryRequest;
import com.example.multirubroproyectproducts.requests.UpdateCategoryRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ICategoryService {

    GenericResponse<Category> createCategory(CategoryRequest request);
    GenericResponse<Category> updateCategory(UpdateCategoryRequest request);

    GenericResponse<List<Category>> getCategories();
    GenericResponse<Category> getCategoryById(UUID uuid);
}