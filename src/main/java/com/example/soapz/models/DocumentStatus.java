package com.example.soapz.models;

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
    private Integer id;

    @Column(name = "status_name", nullable = false, length = 100, unique = true)
    private String statusName;
}
