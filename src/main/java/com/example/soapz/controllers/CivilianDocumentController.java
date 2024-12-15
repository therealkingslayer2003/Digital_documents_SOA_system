package com.example.soapz.controllers;

import com.example.soapz.DTOs.DocumentCreateDTO;
import com.example.soapz.DTOs.DocumentUpdateDTO;
import com.example.soapz.models.Document;
import com.example.soapz.services.CivilianDocumentService;
import com.example.soapz.util.SessionUserIdentifier;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/civilian/docs")
@PreAuthorize("hasRole('Civilian')")
@AllArgsConstructor
public class CivilianDocumentController {
    private final CivilianDocumentService civilianDocumentService;
    private final SessionUserIdentifier sessionUserIdentifier;

    @GetMapping("/all")
    public List<Document> getAllDocuments() {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        return civilianDocumentService.getAllDocuments(userEmail);
    }

    @GetMapping
    public Document getDocumentById(@RequestParam Integer id) {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        return civilianDocumentService.getDocumentById(id, userEmail);
    }

    @DeleteMapping
    public void deleteDocumentById(@RequestParam Integer id) {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        civilianDocumentService.deleteDocumentById(id, userEmail);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Document createNewDocument(@RequestBody @Validated DocumentCreateDTO documentCreateDTO) {
        String userEmail = sessionUserIdentifier.getCurrentUserEmail();

        return civilianDocumentService.createNewDocument(documentCreateDTO, userEmail);
    }

    @PutMapping
    public Document updateDocument(@RequestBody @Validated DocumentUpdateDTO documentUpdateDTO) {
        return civilianDocumentService.updateDocument(documentUpdateDTO);
    }
}
