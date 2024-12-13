package com.example.soapz.controllers;

import com.example.soapz.DTOs.DocumentCreateDTO;
import com.example.soapz.DTOs.DocumentUpdateDTO;
import com.example.soapz.models.Document;
import com.example.soapz.services.CivilianDocumentService;
import com.example.soapz.util.SessionUserIdentifier;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/civilian/docs")
@PreAuthorize("hasRole('Civilian')")
@AllArgsConstructor
public class CivilianDocumentController {
    private final CivilianDocumentService civilianDocumentService;
    private final SessionUserIdentifier sessionUserIdentifier;

    @GetMapping("/get-all")
    public List<Document> getAllDocuments() throws ChangeSetPersister.NotFoundException {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        return civilianDocumentService.getAllDocuments(userEmail);
    }

    @GetMapping("/get/{id}")
    public Document getDocumentById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        return civilianDocumentService.getDocumentById(Integer.valueOf(id), userEmail);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDocumentById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        civilianDocumentService.deleteDocumentById(Integer.valueOf(id), userEmail);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Document createNewDocument(@RequestBody DocumentCreateDTO documentCreateDTO) throws ChangeSetPersister.NotFoundException {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        return civilianDocumentService.createNewDocument(documentCreateDTO, userEmail);
    }

    @PutMapping("/update/{id}")
    public Document updateDocument(@PathVariable String id, @RequestBody DocumentUpdateDTO documentUpdateDTO) throws ChangeSetPersister.NotFoundException {
        return civilianDocumentService.updateDocument(Integer.valueOf(id), documentUpdateDTO);
    }
}
