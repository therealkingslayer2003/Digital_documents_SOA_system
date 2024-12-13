package com.example.soapz.controllers;

import com.example.soapz.models.Document;
import com.example.soapz.services.GovernmentDocumentService;
import com.example.soapz.util.SessionUserIdentifier;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/government/docs")
@PreAuthorize("hasRole('Government')")
@AllArgsConstructor
public class GovernmentDocumentController {
    private final GovernmentDocumentService governmentDocumentService;

    @GetMapping("/get/{id}")
    public Document getDocumentById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return governmentDocumentService.getDocumentById(Integer.valueOf(id));
    }

    @PostMapping("/approve/{id}")
    public void approveDocumentById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        governmentDocumentService.changeDocumentStatus(Integer.valueOf(id), "APPROVED");
    }

    @PostMapping("/decline/{id}")
    public void declineDocumentById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        governmentDocumentService.changeDocumentStatus(Integer.valueOf(id), "DECLINED");
    }
}
