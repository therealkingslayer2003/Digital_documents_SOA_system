package com.example.soapz.services;

import com.example.soapz.DTOs.ServiceCreateDTO;
import com.example.soapz.models.serviceRegistry.Service;
import com.example.soapz.models.serviceRegistry.ServiceCategory;
import com.example.soapz.models.serviceRegistry.ServiceStatus;
import com.example.soapz.repositories.ServiceCategoryRepository;
import com.example.soapz.repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceRegistryService {
    private final ServiceRepository serviceRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

    public ServiceRegistryService(ServiceRepository serviceRepository, ServiceCategoryRepository serviceCategoryRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
    }

    public void registerService(ServiceCreateDTO serviceCreateDTO) {
        ServiceCategory category = serviceCategoryRepository.findById(serviceCreateDTO.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Service service = Service.builder()
                .name(serviceCreateDTO.name())
                .description(serviceCreateDTO.description())
                .url(serviceCreateDTO.url())
                .version(serviceCreateDTO.version())
                .serviceCategory(category)
                .status(ServiceStatus.ACTIVE)
                .build();

        serviceRepository.save(service);
    }

    public Service getServiceByName(String serviceName) {
        return serviceRepository.findByNameIgnoreCase(serviceName)
                .orElseThrow(IllegalArgumentException::new);
    }
}
