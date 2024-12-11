package com.example.soapz.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private DocumentStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private DocumentType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SystemUser user;
}