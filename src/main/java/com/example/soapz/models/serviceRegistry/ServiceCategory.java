package com.example.soapz.models.serviceRegistry;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
