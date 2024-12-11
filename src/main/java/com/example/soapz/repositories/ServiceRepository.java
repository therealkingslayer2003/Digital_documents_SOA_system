package com.example.soapz.repositories;

import com.example.soapz.models.serviceRegistry.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    Optional<Service> findByNameIgnoreCase(String name);
}
