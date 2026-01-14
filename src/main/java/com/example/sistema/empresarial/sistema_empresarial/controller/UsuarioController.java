package com.example.sistema.empresarial.sistema_empresarial.controller;

import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioDTO;
import com.example.sistema.empresarial.sistema_empresarial.dto.UsuarioRegistroDTO;
import com.example.sistema.empresarial.sistema_empresarial.model.UsuarioEntidad;
import com.example.sistema.empresarial.sistema_empresarial.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioEntidad> crearUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        UsuarioEntidad usuarioCreado = usuarioService.crearUsuario(dto);
        return ResponseEntity.ok(usuarioCreado);
    }





    @GetMapping
    public List<UsuarioDTO> getTodosUsuarios() {
        return usuarioService.getTodosUsuarios();
    }



    @PutMapping("/{id}")
    public UsuarioDTO actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.actualizarUsuario(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado con id: " + id;
    }

    @GetMapping("/buscar")
    public List<UsuarioDTO> buscarPorNombre(@RequestParam String nombre) {
        return usuarioService.buscarPorNombre(nombre);
    }
}