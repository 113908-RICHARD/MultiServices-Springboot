package com.example.multirubroproyectproducts.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

        UUID id;
        String description;
        Double price;
        Integer stock;
        List<Category> categories;
        List<Provider> providers;
}
