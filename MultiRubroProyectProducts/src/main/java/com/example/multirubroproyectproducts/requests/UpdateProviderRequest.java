package com.example.multirubroproyectproducts.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class UpdateProviderRequest {
    UUID id;
    String name;
    String address;
    String cellphone;
}
