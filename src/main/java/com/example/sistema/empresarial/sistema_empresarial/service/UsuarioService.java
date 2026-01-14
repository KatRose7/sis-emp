package com.example.sistema.empresarial.sistema_empresarial.service;

import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioDTO;
import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioRegistroDTO;
import com.example.sistema.empresarial.sistema_empresarial.model.UsuarioEntidad;

import java.util.List;

public interface UsuarioService {
    UsuarioEntidad crearUsuario(UsuarioRegistroDTO dto);

    List<UsuarioDTO> getTodosUsuarios();

    UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO);

    void eliminarUsuario(Long id);

    List<UsuarioDTO> buscarPorNombre(String nombre);
}
