package com.example.sistema.empresarial.sistema_empresarial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistema.empresarial.sistema_empresarial.model.Microempresa;

@Repository
public interface MicroempresaRepository extends JpaRepository<Microempresa, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
