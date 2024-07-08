package com.example.multirubroproyectproducts.repositories;

import com.example.multirubroproyectproducts.entities.CategoryEntity;
import com.example.multirubroproyectproducts.entities.ProductEntity;
import com.example.multirubroproyectproducts.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findById(UUID id);
}
