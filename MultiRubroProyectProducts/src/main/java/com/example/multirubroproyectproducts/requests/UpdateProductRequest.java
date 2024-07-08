package com.example.multirubroproyectproducts.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
@Setter
@Getter
public class UpdateProductRequest {

    UUID id;
    String description;
    Double price;

    List<UUID> categories;
    List<UUID> providers;
}
