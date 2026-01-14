package com.example.sistema.empresarial.sistema_empresarial.repository;

import com.example.sistema.empresarial.sistema_empresarial.model.UsuarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UsuarioEntidadRepository extends JpaRepository<UsuarioEntidad,Long> {
    Optional<UsuarioEntidad> findByUsername(String Username);
    List<UsuarioEntidad> findByNombresContainingIgnoreCase(String nombre);
}
