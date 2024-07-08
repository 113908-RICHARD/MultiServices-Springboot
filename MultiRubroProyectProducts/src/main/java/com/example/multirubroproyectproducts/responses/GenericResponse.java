package com.example.multirubroproyectproducts.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class GenericResponse<T> {
    private String status;
    private String message;
    private T data;
}
