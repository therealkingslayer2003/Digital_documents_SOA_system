package com.example.soapz.services;

import com.example.soapz.models.Document;
import com.example.soapz.models.DocumentStatus;
import com.example.soapz.models.serviceRegistry.ServiceStatus;
import com.example.soapz.repositories.DocumentRepository;
import com.example.soapz.repositories.DocumentStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GovernmentDocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentStatusRepository documentStatusRepository;

    public Document getDocumentById(Integer integer) {
        return documentRepository.findById(integer)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void changeDocumentStatus(Integer id, ServiceStatus statusName) {
        Document document = documentRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        DocumentStatus documentStatus = documentStatusRepository.findByStatusName(statusName.name())
                .orElseThrow(IllegalArgumentException::new);

        document.setStatus(documentStatus);

        documentRepository.save(document);
    }
}
