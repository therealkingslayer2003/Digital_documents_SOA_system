package com.example.soapz.repositories;

import com.example.soapz.models.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentStatusRepository extends JpaRepository<DocumentStatus, Integer> {
    Optional<DocumentStatus> findByStatusName(String statusName);
}
