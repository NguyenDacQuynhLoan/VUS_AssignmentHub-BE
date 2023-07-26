package com.edusystem.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.edusystem.configuration.JWTUtil;
import com.edusystem.configuration.SecurityConfig;

import com.edusystem.dto.LoginDto;
import com.edusystem.repositories.Authen.AuthenticateRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {
    private  final SecurityConfig _securityConfig;
    private final AuthenticationManager _authenticationManager;
    private final AuthenticateRepository _authenticateRepository;
    private final JWTUtil _jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> isLogged(@RequestBody LoginDto model)
    {
        var passwordValue = model.getPassword();
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(model.getEmail(),model.getPassword())
        );

        final UserDetails userService = _authenticateRepository.findEmail(model.getEmail());
        if(userService != null){
            String token = _jwtUtil.generateToken(userService);
          return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(404).body("Can not found");
    }
}
