package com.springboot.carservice.models;


import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private UUID id;
    private String brand;
    private String model;
    private String userId;
}
