package com.example.sistema.empresarial.sistema_empresarial.dto;

public record AuthResponse(
        String username,
        String rol,
        String token
) {}