package com.example.sistema.empresarial.sistema_empresarial.controller;

import com.example.sistema.empresarial.sistema_empresarial.dto.PlanDTO;
import com.example.sistema.empresarial.sistema_empresarial.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class PlanController {

  private final PlanService planService;

  // Constructor de PlanController
  public PlanController(PlanService planService) {
    this.planService = planService;
  }

  // Obtener todos los planes
  @GetMapping
  public ResponseEntity<List<PlanDTO>> obtenerPlanes() {
    List<PlanDTO> planes = planService.obtenerPlanes();
    return ResponseEntity.ok(planes);
  }

  // Obtener un plan específico por su ID
  @GetMapping("/{id}")
  public ResponseEntity<PlanDTO> obtenerPlanPorId(@PathVariable Long id) {
    PlanDTO plan = planService.obtenerPlanPorId(id);
    return plan != null ? ResponseEntity.ok(plan) : ResponseEntity.notFound().build();
  }

  // Crear un nuevo plan
  @PostMapping
  public ResponseEntity<PlanDTO> crearPlan(@RequestBody @Valid PlanDTO planDTO) {
    PlanDTO nuevoPlan = planService.crearPlan(planDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPlan);
  }

  // Actualizar un plan existente
  @PutMapping("/{id}")
  public ResponseEntity<PlanDTO> actualizarPlan(@PathVariable Long id, @RequestBody @Valid PlanDTO planDTO) {
    PlanDTO planActualizado = planService.actualizarPlan(id, planDTO);
    return planActualizado != null ? ResponseEntity.ok(planActualizado) : ResponseEntity.notFound().build();
  }

  // Desactivar un plan
  @PatchMapping("/{id}/desactivar")
  public ResponseEntity<Void> desactivarPlan(@PathVariable Long id) {
    boolean desactivado = planService.desactivarPlan(id);
    return desactivado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
  }
}
