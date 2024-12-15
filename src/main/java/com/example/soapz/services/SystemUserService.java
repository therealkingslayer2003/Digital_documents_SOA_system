package com.example.soapz.services;


import com.example.soapz.DTOs.SystemUserCreateDTO;
import com.example.soapz.models.SystemUser;
import com.example.soapz.repositories.RoleRepository;
import com.example.soapz.repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class SystemUserService {
    private final SystemUserRepository systemUserRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(SystemUserCreateDTO systemUserCreateDTO) {
        SystemUser systemUser = new SystemUser();

        systemUser.setName(systemUserCreateDTO.name());
        systemUser.setEmail(systemUserCreateDTO.email());

        //Password encryption
        String encodedPassword = bCryptPasswordEncoder.encode(systemUserCreateDTO.password());
        systemUser.setPassword(encodedPassword);

        systemUser.setSurname(systemUserCreateDTO.surname());

        systemUser.setRole(roleRepository.findById(systemUserCreateDTO.roleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found")));

        systemUserRepository.save(systemUser);
    }
}
