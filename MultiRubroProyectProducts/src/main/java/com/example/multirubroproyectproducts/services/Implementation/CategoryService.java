package com.example.multirubroproyectproducts.services.Implementation;

import com.example.multirubroproyectproducts.entities.CategoryEntity;
import com.example.multirubroproyectproducts.models.Category;
import com.example.multirubroproyectproducts.repositories.CategoryRepository;
import com.example.multirubroproyectproducts.requests.CategoryRequest;
import com.example.multirubroproyectproducts.requests.UpdateCategoryRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.ICategoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService  implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public GenericResponse<Category> createCategory(CategoryRequest request) {
        GenericResponse<Category> response = new GenericResponse<>();
        List<CategoryEntity> categories = categoryRepository.findAll();
        for(CategoryEntity category : categories){
            if(category.getDescription().equals(request.getDescription())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Description already exists");
            }
        }
        try {
            CategoryEntity categoryEntity = categoryRepository.save(modelMapper.map(request, CategoryEntity.class));
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Category created successfully");
            response.setData(modelMapper.map(categoryEntity, Category.class));
        }catch (Exception e){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());

        }
        return response;
    }

    @Override
    @Transactional
    public GenericResponse<Category> updateCategory(UpdateCategoryRequest request) {
        GenericResponse<Category> response = new GenericResponse<>();
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(request.getId());
        if (categoryEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }else{
          CategoryEntity category = categoryEntity.get();
          category.setDescription(request.getDescription());
          categoryRepository.save(category);
          response.setStatus(HttpStatus.OK);
          response.setMessage("Category updated");
          response.setData(modelMapper.map(category, Category.class));
        }
        return response;

    }

    @Override
    public GenericResponse<List<Category>> getCategories() {
        GenericResponse<List<Category>> response = new GenericResponse<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if (categoryEntities.isEmpty()){
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("No categories found");
            return response;
        }
        response.setStatus(HttpStatus.OK);
        response.setMessage(categoryEntities.size() + " categories found");

        List<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities){
            categories.add(modelMapper.map(categoryEntity, Category.class));
        }
        response.setData(categories);
        return response;
    }

    @Override
    public GenericResponse<Category> getCategoryById(UUID uuid) {
        GenericResponse<Category> response = new GenericResponse<>();
       Optional<CategoryEntity> categoryEntity = categoryRepository.findById(uuid);
       if (categoryEntity.isPresent()){
           response.setStatus(HttpStatus.OK);
           response.setMessage("Category found");
           response.setData(modelMapper.map(categoryEntity.get(), Category.class));
       }else {
           response.setStatus(HttpStatus.NOT_FOUND);
           response.setMessage("Category not found");
       }
        return response;
    }
}
