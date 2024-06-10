package com.springboot.userservice.Controllers;


import com.springboot.userservice.Commands.Request.UpdateUserRequest;
import com.springboot.userservice.Commands.Request.UserRequest;
import com.springboot.userservice.Commands.Response.UserResponse;
import com.springboot.userservice.Models.Bike;
import com.springboot.userservice.Models.Car;
import com.springboot.userservice.Services.Imp.UserServiceImp;
import com.springboot.userservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }
    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getCars(id));
    }
    @GetMapping("/bikes/{id}")
    public ResponseEntity<List<Bike>> getBikesByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getBikes(id));
    }
    @PostMapping("/saveCar")
    public ResponseEntity<Car> saveCar( @RequestBody Car car) {
        return ResponseEntity.ok(userService.saveCar(car));

    }
    @PostMapping("/saveBike")
    public ResponseEntity<Bike> saveBike( @RequestBody Bike bike){
        return ResponseEntity.ok(userService.saveBike(bike));
    }
    @GetMapping("/getVehiclesByUser/{id}")
    public ResponseEntity<Map<String,Object>> getVehiclesByUserId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findVehiclesByUserId(id));
    }
}
