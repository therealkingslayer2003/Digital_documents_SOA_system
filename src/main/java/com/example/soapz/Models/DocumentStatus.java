package com.example.soapz.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="document_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false, length = 100, unique = true)
    private String statusName;
}
