package com.example.soapz.services;

import com.example.soapz.DTOs.ServiceCreateDTO;
import com.example.soapz.models.serviceRegistry.Service;
import com.example.soapz.models.serviceRegistry.ServiceCategory;
import com.example.soapz.models.serviceRegistry.ServiceStatus;
import com.example.soapz.repositories.ServiceCategoryRepository;
import com.example.soapz.repositories.ServiceRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

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

    public Service getServiceByName(String serviceName) throws ChangeSetPersister.NotFoundException{
        return serviceRepository.findByNameIgnoreCase(serviceName)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
}
