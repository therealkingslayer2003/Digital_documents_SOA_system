package com.example.soapz.Models.ServiceRegistry;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_category")
@Data // Включает геттеры, сеттеры, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}
