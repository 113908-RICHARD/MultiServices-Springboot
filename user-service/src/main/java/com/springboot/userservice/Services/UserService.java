package com.springboot.userservice.Services;

import com.springboot.userservice.Commands.Request.UpdateUserRequest;
import com.springboot.userservice.Commands.Request.UserRequest;
import com.springboot.userservice.Commands.Response.UserResponse;
import com.springboot.userservice.Models.Bike;
import com.springboot.userservice.Models.Car;
import com.springboot.userservice.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public interface UserService {



    List<UserResponse> findAll();
    UserResponse findById(UUID id);

    UserResponse findByEmail(String email);
    UserResponse updateUser(UpdateUserRequest UpdateUserRequest);
    UserResponse createUser(UserRequest userRequest);
    UserResponse deleteUser(UUID id);
    List<Car> getCars(UUID userId);
    List<Bike> getBikes(UUID userId);
    Car saveCar(Car car);
    Bike saveBike(Bike bike);
    Map<String, Object> findVehiclesByUserId(UUID id);

}
