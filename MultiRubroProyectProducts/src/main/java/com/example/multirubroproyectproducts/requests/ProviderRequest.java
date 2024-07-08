package com.example.multirubroproyectproducts.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ProviderRequest {

    String name;
    String address;
    String cellphone;
}
