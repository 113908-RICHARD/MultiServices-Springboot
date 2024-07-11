package com.example.multirubroproyectproducts.controllers;

import com.example.multirubroproyectproducts.models.Provider;
import com.example.multirubroproyectproducts.requests.ProviderRequest;
import com.example.multirubroproyectproducts.requests.UpdateProviderRequest;
import com.example.multirubroproyectproducts.responses.GenericResponse;
import com.example.multirubroproyectproducts.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    private IProviderService providerService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<Provider>>> getAllProviders() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Provider>> getProviderById(@PathVariable UUID id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }
    @PostMapping
    public ResponseEntity<GenericResponse<Provider>> addProvider(@RequestBody ProviderRequest provider) {
        return ResponseEntity.ok(providerService.createProvider(provider));
    }
    @PutMapping
    public ResponseEntity<GenericResponse<Provider>> updateProvider(@RequestBody UpdateProviderRequest provider) {
        return ResponseEntity.ok(providerService.updateProvider(provider));
    }
}
