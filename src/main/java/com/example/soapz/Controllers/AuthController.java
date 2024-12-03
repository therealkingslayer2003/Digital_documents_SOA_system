package com.example.soapz.Controllers;

import com.example.soapz.DTOs.SystemUserCreateDTO;
import com.example.soapz.DTOs.SystemUserLoginDTO;
import com.example.soapz.Services.JWT.JwtTokenProvider;
import com.example.soapz.Services.SystemUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody SystemUserCreateDTO systemUserCreateDTO) throws ChangeSetPersister.NotFoundException {
        systemUserService.registerUser(systemUserCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@Valid @RequestBody SystemUserLoginDTO systemUserLoginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    systemUserLoginDTO.getEmail(),
                    systemUserLoginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok("Logged in successfully, generated token: " + jwt);
    }

}
