package com.example.soapz.controllers;

import com.example.soapz.models.Document;
import com.example.soapz.models.serviceRegistry.ServiceStatus;
import com.example.soapz.services.GovernmentDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/government/docs")
@PreAuthorize("hasRole('Government')")
@RequiredArgsConstructor
class GovernmentDocumentController {
    private final GovernmentDocumentService governmentDocumentService;

    @GetMapping
    Document getDocumentById(@RequestParam String id) {
        return governmentDocumentService.getDocumentById(Integer.valueOf(id));
    }

    @PutMapping("/{id}")
    void updateStatus(@PathVariable Integer id, @RequestBody ServiceStatus status) {
        governmentDocumentService.changeDocumentStatus(id, status);
    }
}
