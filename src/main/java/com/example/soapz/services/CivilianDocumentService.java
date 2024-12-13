package com.example.soapz.services;

import com.example.soapz.DTOs.DocumentCreateDTO;
import com.example.soapz.DTOs.DocumentUpdateDTO;
import com.example.soapz.models.Document;
import com.example.soapz.models.DocumentStatus;
import com.example.soapz.models.SystemUser;
import com.example.soapz.repositories.DocumentRepository;
import com.example.soapz.repositories.DocumentTypeRepository;
import com.example.soapz.repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CivilianDocumentService {
    private final DocumentRepository documentRepository;
    private final SystemUserRepository systemUserRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public List<Document> getAllDocuments(String userEmail) throws ChangeSetPersister.NotFoundException {
        SystemUser user = systemUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        return documentRepository.findAllByUser(user);
    }

    public Document getDocumentById(Integer id, String userEmail) throws ChangeSetPersister.NotFoundException, AccessDeniedException {
        checkIfCivilianAuthorizedForAction(id, userEmail);

       return documentRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    public void deleteDocumentById(Integer id, String userEmail) throws ChangeSetPersister.NotFoundException, AccessDeniedException {
        checkIfCivilianAuthorizedForAction(id, userEmail);

        documentRepository.deleteById(id);
    }

    public Document createNewDocument(DocumentCreateDTO documentCreateDTO, String userEmail) throws ChangeSetPersister.NotFoundException {
        Document document = new Document();

        document.setTitle(documentCreateDTO.getTitle());
        document.setContent(documentCreateDTO.getContent());

        document.setType(documentTypeRepository.findById(Integer.valueOf(documentCreateDTO.getTypeId()))
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()));

        document.setUser(systemUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()));

        return documentRepository.save(document);
    }

    public Document updateDocument(Integer id, DocumentUpdateDTO documentUpdateDTO) throws ChangeSetPersister.NotFoundException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        document.setContent(documentUpdateDTO.getContent());

        return documentRepository.save(document);
    }

    private void checkIfCivilianAuthorizedForAction(Integer id, String userEmail) throws ChangeSetPersister.NotFoundException, AccessDeniedException {
        SystemUser user = systemUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        List<Document> documents = documentRepository.findAllByUser(user);

        if(!documents.contains(documentRepository.findById(id).orElse(null))){
            throw new AccessDeniedException("You do not have permission to do this action.");
        }
    }
}
