package com.example.soapz.Repositories;

import com.example.soapz.Models.ServiceRegistry.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
}
