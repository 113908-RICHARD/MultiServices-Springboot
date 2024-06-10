package com.springboot.bikeservice.services;

import com.springboot.bikeservice.models.Bike;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BikeService {

    Bike getBikeById(UUID id);
    List<Bike> getAllBike();
    Bike createBike(Bike bike);
    List<Bike> getByUserId(UUID userId);
}
