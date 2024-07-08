package com.example.multirubroproyectproducts.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@Getter
public class UpdateCategoryRequest {
    UUID id;
    String description;
}
