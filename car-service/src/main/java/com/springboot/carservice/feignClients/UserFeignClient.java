package com.springboot.carservice.feignClients;

import com.springboot.carservice.commands.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service")

public interface UserFeignClient {

    @GetMapping("/users/{id}")
    UserResponse getUserById(@PathVariable("id") UUID id);
}
