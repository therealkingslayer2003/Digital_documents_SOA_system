package com.example.soapz.Controllers;

import com.example.soapz.DTOs.ServiceCreateDTO;
import com.example.soapz.Models.ServiceRegistry.Service;
import com.example.soapz.Services.ServiceRegistryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registry")
public class ServiceRegistryController {
    private final ServiceRegistryService serviceRegistryService;

    public ServiceRegistryController(ServiceRegistryService serviceRegistryService){
        this.serviceRegistryService = serviceRegistryService;
    }

    @PostMapping("/new-service")
    public ResponseEntity<Service> registerService(@RequestBody ServiceCreateDTO serviceCreateDTO){
        Service registeredService = serviceRegistryService.registerService(serviceCreateDTO);
        return ResponseEntity.ok(registeredService);
    }
}
