package com.example.soapz.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "signature")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "hash", nullable = false, unique = true, length = 255)
    private String hash;

    @OneToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;
}
