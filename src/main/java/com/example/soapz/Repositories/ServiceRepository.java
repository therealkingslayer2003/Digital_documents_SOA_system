package com.example.soapz.Repositories;

import com.example.soapz.Models.ServiceRegistry.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
