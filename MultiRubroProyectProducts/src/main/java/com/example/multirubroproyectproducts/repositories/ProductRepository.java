package com.example.multirubroproyectproducts.repositories;

import com.example.multirubroproyectproducts.entities.CategoryEntity;
import com.example.multirubroproyectproducts.entities.ProductEntity;
import com.example.multirubroproyectproducts.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findById(UUID id);

//     @Query("SELECT p FROM ProductEntity p JOIN p.providers pr WHERE pr = :provider")
//     Optional<List<ProductEntity>> findProductsByProvider(@Param("provider") ProviderEntity provider);
}
