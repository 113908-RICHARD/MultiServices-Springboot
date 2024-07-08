package com.example.multirubroproyectproducts.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Data
public class ProductRequest {

    String description;
    Double price;
    Integer stock;
    List<UUID> categories;
    List<UUID> providers;

}
