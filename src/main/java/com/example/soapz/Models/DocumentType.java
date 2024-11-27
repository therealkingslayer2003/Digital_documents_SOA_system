package com.example.soapz.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="document_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false, length = 100, unique = true)
    private String typeName;
}
