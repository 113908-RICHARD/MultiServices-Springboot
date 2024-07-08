package com.springboot.userservice.Controllers;


import com.springboot.userservice.Commands.Request.UpdateUserRequest;
import com.springboot.userservice.Commands.Request.UserRequest;
import com.springboot.userservice.Commands.Response.UserResponse;
import com.springboot.userservice.Models.Bike;
import com.springboot.userservice.Models.Car;
import com.springboot.userservice.Services.Imp.UserServiceImp;
import com.springboot.userservice.Services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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



    @GetMapping("/helloUser")
    @PreAuthorize("hasRole('client_user')")
    public String helloUser(){
        return "hello user";
    }
    @GetMapping("/helloAdmin")
    @PreAuthorize("hasRole('client_admin')")
    public String helloAdmin(){
        return "hello admin";
    }


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

    @CircuitBreaker(name = "carCB",fallbackMethod = "fallBackGetCars")
    @GetMapping("/cars/{id}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getCars(id));
    }
    @CircuitBreaker(name = "bikeCB",fallbackMethod = "fallBackGetBikes")
    @GetMapping("/bikes/{id}")
    public ResponseEntity<List<Bike>> getBikesByUserId(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getBikes(id));
    }
    @CircuitBreaker(name = "carCB",fallbackMethod = "fallBackSaveCar")
    @PostMapping("/saveCar")
    public ResponseEntity<Car> saveCar( @RequestBody Car car) {
        return ResponseEntity.ok(userService.saveCar(car));

    }
    @CircuitBreaker(name = "bikeCB",fallbackMethod = "fallBackSaveBike")
    @PostMapping("/saveBike")
    public ResponseEntity<Bike> saveBike( @RequestBody Bike bike){
        return ResponseEntity.ok(userService.saveBike(bike));
    }
    @CircuitBreaker(name = "vehiclesCB",fallbackMethod = "fallBackGetVehicles")
    @GetMapping("/getVehiclesByUser/{id}")
    public ResponseEntity<Map<String,Object>> getVehiclesByUserId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findVehiclesByUserId(id));
    }

    public ResponseEntity<List<Car>> fallBackGetCars(@PathVariable UUID id,RuntimeException e){
        return new  ResponseEntity("fallback method string for getCarsMethod for user id: "+id, HttpStatus.OK );
    }
    public ResponseEntity<Car> fallBackSaveCar( @RequestBody Car car,RuntimeException e){
        return new ResponseEntity("fallback method string for saveCar for user id: "+car.getUserId(),HttpStatus.OK);
    }

    public ResponseEntity<List<Bike>> fallBackGetBikes(@PathVariable UUID id,RuntimeException e){
        return new ResponseEntity("fallback method string for getBikes for user id: "+id,HttpStatus.OK);
    }
    public ResponseEntity<Bike> fallBackSaveBike( @RequestBody Bike bike,RuntimeException e){
        return new ResponseEntity("fallback method string for saveBike for user id: "+bike.getUserId(),HttpStatus.OK);
    }
    public ResponseEntity<Map<String,Object>> fallBackGetVehicles(@PathVariable("id") UUID id,RuntimeException e){
        return new ResponseEntity("fallback method string for getVehicles for user id: "+id,HttpStatus.OK);
    }
}
