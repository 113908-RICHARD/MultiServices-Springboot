package com.springboot.bikeservice.models;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bike {


    public UUID id;
    public String brand;
    public String model;
    public UUID userId;

}
