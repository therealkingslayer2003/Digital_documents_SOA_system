package com.example.soapz.Repositories;

import com.example.soapz.Models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {
    Optional<SystemUser> findByEmail(String email);
}
