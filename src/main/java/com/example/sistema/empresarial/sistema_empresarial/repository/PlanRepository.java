package com.example.sistema.empresarial.sistema_empresarial.repository;

import com.example.sistema.empresarial.sistema_empresarial.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // Puedes agregar métodos personalizados si es necesario
}

    

