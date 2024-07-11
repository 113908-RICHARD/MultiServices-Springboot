package com.example.multirubroproyectproducts.services.Implementation;

import com.example.multirubroproyectproducts.entities.ProductEntity;
import com.example.multirubroproyectproducts.entities.ProviderEntity;
import com.example.multirubroproyectproducts.models.Provider;
import com.example.multirubroproyectproducts.repositories.ProductRepository;
import com.example.multirubroproyectproducts.repositories.ProviderRepository;
import com.example.multirubroproyectproducts.requests.ProviderRequest;
import com.example.multirubroproyectproducts.requests.UpdateProviderRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.IProviderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService implements IProviderService {

    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public GenericResponse<List<Provider>> getAllProviders() {
        GenericResponse<List<Provider>> response = new GenericResponse<>();
        List<ProviderEntity> providers = providerRepository.findAll();
        if (providers.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("No providers found");

        }else{
            response.setStatus(HttpStatus.OK);
            response.setMessage("Found " + providers.size() + " providers");

            List<Provider> providerList = new ArrayList<>();
            for(ProviderEntity provider : providers) {
                providerList.add(modelMapper.map(provider, Provider.class));
            }
            response.setData(providerList);
        }
        return response;
    }

    @Override
    public GenericResponse<Provider> getProviderById(UUID id) {
        GenericResponse<Provider> response = new GenericResponse<>();
        Optional<ProviderEntity> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            response.setStatus(HttpStatus.OK);
            response.setMessage("Found " + provider.get().getName());
            response.setData(modelMapper.map(provider.get(), Provider.class));
        }else{
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("No provider found with id " + id);
        }
        return response;
    }

    @Override
    @Transactional
    public GenericResponse<Provider> createProvider(ProviderRequest provider) {
        GenericResponse<Provider> response = new GenericResponse<>();
        ProviderEntity providerEntity = modelMapper.map(provider, ProviderEntity.class);
        providerEntity.setProducts(new ArrayList<>());
        try {
            providerRepository.save(providerEntity);
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Created " + providerEntity.getName());
            response.setData(modelMapper.map(providerEntity, Provider.class));
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error creating provider");
        }
        return response;
    }

    @Override
    @Transactional
    public GenericResponse<Provider> updateProvider(UpdateProviderRequest request) {
        GenericResponse<Provider> response = new GenericResponse<>();
        Optional<ProviderEntity> providerEntity = providerRepository.findById(request.getId());
        if (providerEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No provider with id " + request.getId() + " found");
        }
        ProviderEntity providerEntityToUpdate = providerEntity.get();
        providerEntityToUpdate.setName(request.getName());
        providerEntityToUpdate.setAddress(request.getAddress());
        providerEntityToUpdate.setCellphone(request.getCellphone());
        providerEntityToUpdate.setProducts(getProducts(request.getProducts()));
        try {
            providerRepository.save(providerEntityToUpdate);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Updated " + providerEntityToUpdate.getName());
            response.setData(modelMapper.map(providerEntityToUpdate, Provider.class));
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error updating provider");
        }
        return response;
    }

    private List<ProductEntity> getProducts(List<UUID> productIds) {
        List<ProductEntity> products = new ArrayList<>();
        for (UUID productId : productIds) {
            ProductEntity productEntity = productRepository.findById(productId).get();
            products.add(productEntity);
        }
        return products;
    }
}
