package com.example.soapz.Repositories;

import com.example.soapz.Models.ServiceRegistry.Service;
import com.example.soapz.Models.ServiceRegistry.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
