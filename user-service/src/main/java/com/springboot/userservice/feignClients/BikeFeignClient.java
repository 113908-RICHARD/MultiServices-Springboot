package com.springboot.userservice.feignClients;

import com.springboot.userservice.Models.Bike;
import com.springboot.userservice.Models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "bike-service",url = "http://localhost:8082")
public interface BikeFeignClient {

    @PostMapping("/bikes")
    Bike saveBike(@RequestBody Bike bike);
    @GetMapping("bikes/user/{id}")
    List<Bike> findBikesByUserId(@PathVariable("id") UUID id);
}
