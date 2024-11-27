package com.example.soapz.Repositories;

import com.example.soapz.Models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
