package com.example.sistema.empresarial.sistema_empresarial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroDTO(
        @NotBlank(message = "Nombre obligatorio")
        String nombres,

        @NotBlank(message = "Apellido obligatorio")
        String apellidos,

        @NotBlank(message = "CI obligatorio")
        String ci,

        String direccion,

        @Email(message = "Email inválido")
        String email,

        String telefono,

        @NotBlank(message = "Username obligatorio")
        String username,

        @Size(min = 6, message = "Password mínimo 6 caracteres")
        String password,

        String rol
) {
}
