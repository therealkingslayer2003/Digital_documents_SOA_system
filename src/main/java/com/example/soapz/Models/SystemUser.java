package com.example.soapz.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",nullable = false, length = 50)
    private String name;

    @Column(name = "surname",nullable = false, length = 50)
    private String surname;

    @Column(name = "email",nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;
}
