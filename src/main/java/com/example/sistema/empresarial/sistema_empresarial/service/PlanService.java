package com.example.sistema.empresarial.sistema_empresarial.service;

import com.example.sistema.empresarial.sistema_empresarial.model.Plan;
import com.example.sistema.empresarial.sistema_empresarial.dto.PlanDTO;
import com.example.sistema.empresarial.sistema_empresarial.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

  private final PlanRepository planRepository;

  public PlanService(PlanRepository planRepository) {
    this.planRepository = planRepository;
  }

  // Obtener todos los planes
  public List<PlanDTO> obtenerPlanes() {
    List<Plan> planes = planRepository.findAll();
    List<PlanDTO> planDTOs = new ArrayList<>();
    for (Plan plan : planes) {
      planDTOs.add(convertirAPlanDTO(plan));
    }
    return planDTOs;
  }

  // Obtener un plan específico por ID
  public PlanDTO obtenerPlanPorId(Long id) {
    Plan plan = planRepository.findById(id).orElse(null);
    return plan != null ? convertirAPlanDTO(plan) : null;
  }

  // Crear un nuevo plan
  public PlanDTO crearPlan(PlanDTO planDTO) {
    Plan plan = new Plan();
    plan.setNombre(planDTO.getNombre());
    plan.setDescripcion(planDTO.getDescripcion());
    plan.setPrecio(planDTO.getPrecio());
    plan.setEstado(planDTO.getEstado());
    plan = planRepository.save(plan);
    return convertirAPlanDTO(plan);
  }

  // Actualizar un plan existente
  public PlanDTO actualizarPlan(Long id, PlanDTO planDTO) {
    Plan plan = planRepository.findById(id).orElse(null);
    if (plan != null) {
      plan.setNombre(planDTO.getNombre());
      plan.setDescripcion(planDTO.getDescripcion());
      plan.setPrecio(planDTO.getPrecio());
      plan.setEstado(planDTO.getEstado());
      plan = planRepository.save(plan);
      return convertirAPlanDTO(plan);
    }
    return null;
  }

  // Desactivar un plan
  public boolean desactivarPlan(Long id) {
    Plan plan = planRepository.findById(id).orElse(null);
    if (plan != null) {
      plan.setEstado("INACTIVO");
      planRepository.save(plan);
      return true;
    }
    return false;
  }

  // Convertir de Plan a PlanDTO
  private PlanDTO convertirAPlanDTO(Plan plan) {
    PlanDTO planDTO = new PlanDTO();
    planDTO.setId(plan.getId());
    planDTO.setNombre(plan.getNombre());
    planDTO.setDescripcion(plan.getDescripcion());
    planDTO.setPrecio(plan.getPrecio());
    planDTO.setEstado(plan.getEstado());
    return planDTO;
  }
}
