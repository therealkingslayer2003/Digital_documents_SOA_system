package com.example.soapz.services;

import com.example.soapz.DTOs.DocumentCreateDTO;
import com.example.soapz.DTOs.DocumentUpdateDTO;
import com.example.soapz.models.Document;
import com.example.soapz.models.SystemUser;
import com.example.soapz.repositories.DocumentRepository;
import com.example.soapz.repositories.DocumentTypeRepository;
import com.example.soapz.repositories.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CivilianDocumentService {
    private final DocumentRepository documentRepository;
    private final SystemUserRepository systemUserRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public List<Document> getAllDocuments(String userEmail) {
        SystemUser user = systemUserRepository.findByEmail(userEmail)
                .orElseThrow(IllegalArgumentException::new);

        return documentRepository.findAllByUser(user);
    }

    public Document getDocumentById(Integer id, String userEmail) {
        checkIfCivilianAuthorizedForAction(id, userEmail);

        return documentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void deleteDocumentById(Integer id, String userEmail) {
        checkIfCivilianAuthorizedForAction(id, userEmail);

        documentRepository.deleteById(id);
    }

    public Document createNewDocument(DocumentCreateDTO documentCreateDTO, String userEmail) {
        Document document = new Document();

        document.setTitle(documentCreateDTO.title());
        document.setContent(documentCreateDTO.content());

        document.setType(documentTypeRepository.findById(Integer.valueOf(documentCreateDTO.typeId()))
                .orElseThrow(IllegalArgumentException::new));

        document.setUser(systemUserRepository.findByEmail(userEmail)
                .orElseThrow(IllegalArgumentException::new));

        return documentRepository.save(document);
    }

    public Document updateDocument(DocumentUpdateDTO documentUpdateDTO) {
        Document document = documentRepository.findById(documentUpdateDTO.id())
                .orElseThrow(IllegalArgumentException::new);

        document.setContent(documentUpdateDTO.content());

        return documentRepository.save(document);
    }

    private void checkIfCivilianAuthorizedForAction(Integer id, String userEmail) {
        SystemUser user = systemUserRepository.findByEmail(userEmail)
                .orElseThrow(IllegalArgumentException::new);

        List<Document> documents = documentRepository.findAllByUser(user);
        Document retrievedDocument = documentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        if (!documents.contains(retrievedDocument)) {
            throw new AccessDeniedException("You do not have permission to do this action.");
        }
    }
}
