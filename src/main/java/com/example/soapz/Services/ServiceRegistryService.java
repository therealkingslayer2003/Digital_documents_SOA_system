package com.example.soapz.Services;

import com.example.soapz.DTOs.ServiceCreateDTO;
import com.example.soapz.Models.ServiceRegistry.Service;
import com.example.soapz.Models.ServiceRegistry.ServiceCategory;
import com.example.soapz.Models.ServiceRegistry.ServiceStatus;
import com.example.soapz.Repositories.ServiceCategoryRepository;
import com.example.soapz.Repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceRegistryService {
    private final ServiceRepository serviceRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

    public ServiceRegistryService(ServiceRepository serviceRepository, ServiceCategoryRepository serviceCategoryRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceCategoryRepository = serviceCategoryRepository;
    }

    public Service registerService(ServiceCreateDTO serviceCreateDTO) {
        ServiceCategory category = serviceCategoryRepository.findById(serviceCreateDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Service service = new Service();
        service.setName(serviceCreateDTO.getName());
        service.setDescription(serviceCreateDTO.getDescription());
        service.setUrl(serviceCreateDTO.getUrl());
        service.setVersion(serviceCreateDTO.getVersion());
        service.setServiceCategory(category);
        service.setStatus(ServiceStatus.ACTIVE);

        return serviceRepository.save(service);
    }

}
