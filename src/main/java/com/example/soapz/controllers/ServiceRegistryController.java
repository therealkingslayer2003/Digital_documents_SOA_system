package com.example.soapz.controllers;

import com.example.soapz.DTOs.ServiceCreateDTO;
import com.example.soapz.models.serviceRegistry.Service;
import com.example.soapz.services.ServiceRegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-registry")
@PreAuthorize("hasRole('Government')")
@RequiredArgsConstructor
class ServiceRegistryController {
    private final ServiceRegistryService serviceRegistryService;


    @PostMapping
    void registerService(@RequestBody ServiceCreateDTO serviceCreateDTO){
        serviceRegistryService.registerService(serviceCreateDTO);
    }

    @GetMapping
    Service getServiceByName(@RequestParam String serviceName) {
        return serviceRegistryService.getServiceByName(serviceName);
    }

}
