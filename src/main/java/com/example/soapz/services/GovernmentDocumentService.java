package com.example.soapz.services;

import com.example.soapz.models.Document;
import com.example.soapz.models.DocumentStatus;
import com.example.soapz.repositories.DocumentRepository;
import com.example.soapz.repositories.DocumentStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GovernmentDocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentStatusRepository documentStatusRepository;

    public Document getDocumentById(Integer integer) throws ChangeSetPersister.NotFoundException {
        return documentRepository.findById(integer)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public void changeDocumentStatus(Integer id, String statusName) throws ChangeSetPersister.NotFoundException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        DocumentStatus documentStatus = documentStatusRepository.findByStatusName(statusName)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        document.setStatus(documentStatus);

        documentRepository.save(document);
    }
}
