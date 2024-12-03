package com.example.soapz.Controllers;

import com.example.soapz.DTOs.SystemUserCreateDTO;
import com.example.soapz.Services.SystemUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController{
    //private final AuthenticationManager authenticationManager;
    //private final JwtTokenProvider jwtTokenProvider;
    private final SystemUserService systemUserService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody SystemUserCreateDTO systemUserCreateDTO){
        try{
            systemUserService.registerUser(systemUserCreateDTO);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ChangeSetPersister.NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //login !
}
