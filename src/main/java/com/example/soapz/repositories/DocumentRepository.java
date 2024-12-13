package com.example.soapz.repositories;

import com.example.soapz.models.Document;
import com.example.soapz.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findAllByUser(SystemUser user);
}
