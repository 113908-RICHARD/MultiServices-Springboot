package com.springboot.bikeservice.repositories;

import com.springboot.bikeservice.entities.BikeEntity;
import com.springboot.bikeservice.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BikeRepository extends JpaRepository<BikeEntity, UUID> {

    Optional<BikeEntity> findById(UUID id);
    Optional<List<BikeEntity>> findByUserId(UUID userId);
}
