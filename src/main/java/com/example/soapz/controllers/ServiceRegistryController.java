package com.example.soapz.controllers;

import com.example.soapz.DTOs.ServiceCreateDTO;
import com.example.soapz.models.serviceRegistry.Service;
import com.example.soapz.services.ServiceRegistryService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.imageio.spi.ServiceRegistry;

@RestController
@RequestMapping("/api/service-registry")
@PreAuthorize("hasRole('Government')")
@AllArgsConstructor
public class ServiceRegistryController {
    private final ServiceRegistryService serviceRegistryService;


    @PostMapping("/new-service")
    public void registerService(@RequestBody ServiceCreateDTO serviceCreateDTO){
        serviceRegistryService.registerService(serviceCreateDTO);
    }

    @GetMapping("/service/{serviceName}")
    public Service getServiceByName(@PathVariable String serviceName) throws ChangeSetPersister.NotFoundException {
        return serviceRegistryService.getServiceByName(serviceName);
    }

}
