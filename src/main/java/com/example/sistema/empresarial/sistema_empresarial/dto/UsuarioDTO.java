package com.example.sistema.empresarial.sistema_empresarial.dto;

import java.time.LocalDateTime;

public record UsuarioDTO(
        Long idUsuario,
        String nombres,
        String apellidos,
        String ci,
        String direccion,
        String email,
        String telefono,
        String estado,
        String username,
        String password,
        LocalDateTime fechaRegistro,
        String idTenat,
        String rol
) {}
