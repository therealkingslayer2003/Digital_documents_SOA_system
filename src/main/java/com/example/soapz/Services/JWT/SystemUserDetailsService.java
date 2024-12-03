package com.example.soapz.Services.JWT;

import com.example.soapz.Models.SystemUser;
import com.example.soapz.Repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SystemUserDetailsService implements UserDetailsService {
    private final SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> user = systemUserRepository.findByEmail(username);

        if(user.isPresent()) {
            return new SystemUserDetails(user.get());
        }
        else{
            throw new UsernameNotFoundException(username + " not found");
        }
    }
}
