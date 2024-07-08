package com.example.multirubroproyectproducts.services.Implementation;

import com.example.multirubroproyectproducts.entities.CategoryEntity;
import com.example.multirubroproyectproducts.models.Category;
import com.example.multirubroproyectproducts.repositories.CategoryRepository;
import com.example.multirubroproyectproducts.requests.CategoryRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService  implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public GenericResponse<Category> createCategory(CategoryRequest request) {
        return null;
    }

    @Override
    public GenericResponse<Category> updateCategory(CategoryRequest request) {
        return null;
    }

    @Override
    public GenericResponse<Category> getCategories() {
        GenericResponse<Category> response = new GenericResponse<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if (categoryEntities.isEmpty()){

        }
    }

    @Override
    public GenericResponse<Category> getCategoryById(UUID uuid) {
        return null;
    }
}
