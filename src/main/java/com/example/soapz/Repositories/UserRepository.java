package com.example.soapz.Repositories;

import com.example.soapz.Models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SystemUser, Long> {
}
