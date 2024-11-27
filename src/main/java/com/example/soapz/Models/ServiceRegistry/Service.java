package com.example.soapz.Models.ServiceRegistry;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "url", nullable = false, unique = true, length = 255)
    private String url;

    @Column(name = "version", nullable = false, length = 20)
    private String version;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ServiceStatus status;
}
