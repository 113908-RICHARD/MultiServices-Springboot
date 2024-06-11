package com.springboot.userservice.feignClients;

import com.springboot.userservice.Models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "car-service")

public interface carFeignClient {

    @PostMapping("/cars")
    Car saveCar(@RequestBody Car car);

    @GetMapping("cars/user/{id}")
    List<Car> findCarsByUserId(@PathVariable("id") UUID id);
}
