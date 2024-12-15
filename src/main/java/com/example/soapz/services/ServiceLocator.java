package com.example.soapz.services;

import com.example.soapz.models.serviceRegistry.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.example.soapz.models.serviceRegistry.ServiceStatus.ACTIVE;

@org.springframework.stereotype.Service
public class ServiceLocator {
    private final RestTemplate restTemplate;

    @Value("${service.registry.url}")
    private String serviceRegistryUrl;

    public ServiceLocator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getServiceUrl(String serviceName) {
        String url = serviceRegistryUrl + serviceName;

        ResponseEntity<Service> response = restTemplate.getForEntity(url, Service.class);

        if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Service service = response.getBody();
            if(ACTIVE == service.getStatus()) {
                return service.getUrl();
            }
        }
        throw new RuntimeException("Service" + serviceName + " not found");
    }

}
