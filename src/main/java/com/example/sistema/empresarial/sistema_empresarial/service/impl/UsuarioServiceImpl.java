package com.example.sistema.empresarial.sistema_empresarial.service.impl;

import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioDTO;
import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioRegistroDTO;
import com.example.sistema.empresarial.sistema_empresarial.mapper.UsuarioMapper;
import com.example.sistema.empresarial.sistema_empresarial.model.UsuarioEntidad;
import com.example.sistema.empresarial.sistema_empresarial.repository.UsuarioEntidadRepository;
import com.example.sistema.empresarial.sistema_empresarial.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioEntidadRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioEntidadRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioEntidad crearUsuario(UsuarioRegistroDTO dto) {
        UsuarioEntidad usuario = new UsuarioEntidad();

        usuario.setNombres(dto.nombres());
        usuario.setApellidos(dto.apellidos());
        usuario.setCi(dto.ci());
        usuario.setDireccion(dto.direccion());
        usuario.setEmail(dto.email());
        usuario.setTelefono(dto.telefono());
        usuario.setUsername(dto.username());

        // Encriptar password
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        // Si no se envía rol, asigna ROLE_USER por defecto
        usuario.setRol(dto.rol() != null ? dto.rol() : "ROLE_USER");
        usuario.setEstado("ACTIVO");
        usuario.setFechaRegistro(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }






    @Override
    public List<UsuarioDTO> getTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }



    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<UsuarioEntidad> usuarioExistenteOpt = usuarioRepository.findById(id);
        if (usuarioExistenteOpt.isPresent()) {
            UsuarioEntidad usuarioExistente = usuarioExistenteOpt.get();
            // Actualizar campos
            usuarioExistente.setNombres(usuarioDTO.nombres());
            usuarioExistente.setApellidos(usuarioDTO.apellidos());
            usuarioExistente.setCi(usuarioDTO.ci());
            usuarioExistente.setDireccion(usuarioDTO.direccion());
            usuarioExistente.setEmail(usuarioDTO.email());
            usuarioExistente.setTelefono(usuarioDTO.telefono());
            usuarioExistente.setEstado(usuarioDTO.estado());
            usuarioExistente.setUsername(usuarioDTO.username());
            usuarioExistente.setPassword(usuarioDTO.password());
            usuarioExistente.setRol(usuarioDTO.rol());
            usuarioExistente.setIdTenat(usuarioDTO.idTenat());
            // Fecha de registro normalmente no se modifica
            UsuarioEntidad actualizado = usuarioRepository.save(usuarioExistente);
            return UsuarioMapper.toDTO(actualizado);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    @Override
    public List<UsuarioDTO> buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombresContainingIgnoreCase(nombre)
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

}
