package com.springboot.bikeservice.controllers;


import com.springboot.bikeservice.models.Bike;
import com.springboot.bikeservice.services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bikes")
public class Controllers {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes() {
        return ResponseEntity.ok(bikeService.getAllBike());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable UUID id) {
        return ResponseEntity.ok(bikeService.getBikeById(id));
    }
    @PostMapping
    public ResponseEntity<Bike> createBike(@RequestBody Bike bike) {
        return ResponseEntity.ok(bikeService.createBike(bike));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(bikeService.getByUserId(id));
    }
}
