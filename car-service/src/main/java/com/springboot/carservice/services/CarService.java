package com.springboot.carservice.services;

import com.springboot.carservice.commands.UserResponse;
import com.springboot.carservice.models.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CarService {

    Car getCarById(UUID id);
    List<Car> getAllCars();
    Car createCar(Car car);
    List<Car> getCarsByUserId(UUID userId) ;


}
