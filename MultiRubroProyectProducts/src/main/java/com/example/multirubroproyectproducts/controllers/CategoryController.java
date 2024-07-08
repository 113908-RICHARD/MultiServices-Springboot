package com.example.multirubroproyectproducts.controllers;

import com.example.multirubroproyectproducts.models.Category;
import com.example.multirubroproyectproducts.requests.CategoryRequest;
import com.example.multirubroproyectproducts.requests.UpdateCategoryRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.ICategoryService;
import com.example.multirubroproyectproducts.services.Implementation.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Category>> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
    @PostMapping
    public ResponseEntity<GenericResponse<Category>> addCategory(@RequestBody CategoryRequest category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }
    @PutMapping
    public ResponseEntity<GenericResponse<Category>> updateCategory(@RequestBody UpdateCategoryRequest category) {
        return ResponseEntity.ok(categoryService.updateCategory(category));
    }
}
