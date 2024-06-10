package com.springboot.carservice.repositories;

import com.springboot.carservice.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, UUID> {
    Optional<CarEntity> findById(UUID id);
    Optional<List<CarEntity>> findByUserId(UUID userId);

}
