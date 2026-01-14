package com.example.sistema.empresarial.sistema_empresarial.mapper;

import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioDTO;
import com.example.sistema.empresarial.sistema_empresarial.model.UsuarioEntidad;

public class UsuarioMapper {
    // Entidad → DTO
    public static UsuarioDTO toDTO(UsuarioEntidad usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCi(),
                usuario.getDireccion(),
                usuario.getEmail(),
                usuario.getTelefono(),
                usuario.getEstado(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getFechaRegistro(),
                usuario.getIdTenat(),
                usuario.getRol()
        );
    }

    // DTO → Entidad
    public static UsuarioEntidad toEntity(UsuarioDTO dto) {
        UsuarioEntidad usuario = new UsuarioEntidad();
        usuario.setIdUsuario(dto.idUsuario());
        usuario.setNombres(dto.nombres());
        usuario.setApellidos(dto.apellidos());
        usuario.setCi(dto.ci());
        usuario.setDireccion(dto.direccion());
        usuario.setEmail(dto.email());
        usuario.setTelefono(dto.telefono());
        usuario.setEstado(dto.estado());
        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
        usuario.setFechaRegistro(dto.fechaRegistro());
        usuario.setIdTenat(dto.idTenat());
        usuario.setRol(dto.rol());
        return usuario;
    }
}
