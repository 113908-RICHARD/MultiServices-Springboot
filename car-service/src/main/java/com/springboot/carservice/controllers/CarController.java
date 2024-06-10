package com.springboot.carservice.controllers;


import com.springboot.carservice.models.Car;
import com.springboot.carservice.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(carService.getCarById(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("id") UUID id){
        return ResponseEntity.ok(carService.getCarsByUserId(id));
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        return ResponseEntity.ok(carService.createCar(car));
    }
}
