package com.example.soapz.controllers;

import com.example.soapz.DTOs.SystemUserCreateDTO;
import com.example.soapz.DTOs.SystemUserLoginDTO;
import com.example.soapz.services.JWT.JwtTokenProvider;
import com.example.soapz.services.SystemUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SystemUserService systemUserService;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody SystemUserCreateDTO systemUserCreateDTO) throws ChangeSetPersister.NotFoundException {
        systemUserService.registerUser(systemUserCreateDTO);
    }

    @PostMapping("/login")
    public String authenticate(@Valid @RequestBody SystemUserLoginDTO systemUserLoginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    systemUserLoginDTO.getEmail(),
                    systemUserLoginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return "Logged in successfully, generated token: " + jwt;
    }

}
