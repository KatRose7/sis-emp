package com.example.sistema.empresarial.sistema_empresarial.controller;

import com.example.sistema.empresarial.sistema_empresarial.dto.AuthRequest;
import com.example.sistema.empresarial.sistema_empresarial.dto.AuthResponse;
import com.example.sistema.empresarial.sistema_empresarial.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager am, JwtUtil jwtUtil) {
        this.authManager = am;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtUtil.generarToken(user);
        String rol = user.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority())
                .orElse("ROLE_USER");

        return new AuthResponse(user.getUsername(), rol, token);
    }
}
