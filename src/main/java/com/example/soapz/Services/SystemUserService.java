package com.example.soapz.Services;


import com.example.soapz.DTOs.SystemUserCreateDTO;
import com.example.soapz.Models.SystemUser;
import com.example.soapz.Repositories.RoleRepository;
import com.example.soapz.Repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class SystemUserService {
    private final SystemUserRepository systemUserRepository;
    private final RoleRepository roleRepository;

    public void registerUser(SystemUserCreateDTO systemUserCreateDTO) throws ChangeSetPersister.NotFoundException {
        SystemUser systemUser = new SystemUser();

        systemUser.setName(systemUserCreateDTO.getName());
        systemUser.setEmail(systemUserCreateDTO.getEmail());
        systemUser.setPassword(systemUserCreateDTO.getPassword());
        systemUser.setSurname(systemUserCreateDTO.getSurname());
        systemUser.setRole(roleRepository.findById(systemUserCreateDTO.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found")));

        systemUserRepository.save(systemUser);
    }
}
