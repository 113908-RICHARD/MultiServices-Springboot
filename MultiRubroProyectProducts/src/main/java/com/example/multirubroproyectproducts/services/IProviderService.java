package com.example.multirubroproyectproducts.services;

import com.example.multirubroproyectproducts.models.Provider;
import com.example.multirubroproyectproducts.requests.ProviderRequest;
import com.example.multirubroproyectproducts.requests.UpdateProviderRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IProviderService {

    GenericResponse<List<Provider>> getAllProviders();
    GenericResponse<Provider> getProviderById(UUID id);
    GenericResponse<Provider> createProvider(ProviderRequest provider);
    GenericResponse<Provider> updateProvider(UpdateProviderRequest request);

}
