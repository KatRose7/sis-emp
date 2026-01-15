package com.example.sistema.empresarial.sistema_empresarial.service;

import org.springframework.stereotype.Service;

import com.example.sistema.empresarial.sistema_empresarial.dto.MicroempresaDTO;
import com.example.sistema.empresarial.sistema_empresarial.model.Microempresa;
import com.example.sistema.empresarial.sistema_empresarial.repository.MicroempresaRepository;
import com.example.sistema.empresarial.sistema_empresarial.repository.PlanRepository;

@Service
public class MicroempresaService {

    private final MicroempresaRepository microempresaRepository;
    private final PlanRepository planRepository;

    public MicroempresaService(MicroempresaRepository microempresaRepository, PlanRepository planRepository) {
        this.microempresaRepository = microempresaRepository;
        this.planRepository = planRepository;
    }

    // Registrar una nueva microempresa
    public MicroempresaDTO registrarMicroempresa(MicroempresaDTO microempresaDTO) {
        Microempresa microempresa = new Microempresa();
        microempresa.setRazonSocial(microempresaDTO.getRazonSocial());
        microempresa.setNombreComercial(microempresaDTO.getNombreComercial());
        microempresa.setNit(microempresaDTO.getNit());
        microempresa.setDireccion(microempresaDTO.getDireccion());
        microempresa.setCiudad(microempresaDTO.getCiudad());
        microempresa.setTelefono(microempresaDTO.getTelefono());
        microempresa.setEmail(microempresaDTO.getEmail());
        microempresa.setEstado(microempresaDTO.getEstado());
        microempresa.setFechaRegistro(microempresaDTO.getFechaRegistro());
        
        // Asociar el plan con la microempresa
        planRepository.findById(microempresaDTO.getIdPlan()).ifPresent(microempresa::setPlan);

        microempresa = microempresaRepository.save(microempresa);
        return convertirAMicroempresaDTO(microempresa);
    }

    // Actualizar una microempresa existente
    public MicroempresaDTO actualizarMicroempresa(Long idTenant, MicroempresaDTO microempresaDTO) {
        Microempresa microempresa = microempresaRepository.findById(idTenant).orElse(null);
        
        if (microempresa != null) {
            microempresa.setRazonSocial(microempresaDTO.getRazonSocial());
            microempresa.setNombreComercial(microempresaDTO.getNombreComercial());
            microempresa.setNit(microempresaDTO.getNit());
            microempresa.setDireccion(microempresaDTO.getDireccion());
            microempresa.setCiudad(microempresaDTO.getCiudad());
            microempresa.setTelefono(microempresaDTO.getTelefono());
            microempresa.setEmail(microempresaDTO.getEmail());
            microempresa.setEstado(microempresaDTO.getEstado());
            microempresa.setFechaRegistro(microempresaDTO.getFechaRegistro());

            // Actualizamos el plan
            planRepository.findById(microempresaDTO.getIdPlan()).ifPresent(microempresa::setPlan);
            
            microempresa = microempresaRepository.save(microempresa);
            return convertirAMicroempresaDTO(microempresa);
        }
        return null;  // Si la microempresa no existe
    }

    // Convertir de Microempresa a MicroempresaDTO
    private MicroempresaDTO convertirAMicroempresaDTO(Microempresa microempresa) {
        MicroempresaDTO microempresaDTO = new MicroempresaDTO();
        microempresaDTO.setIdTenant(microempresa.getIdTenant());
        microempresaDTO.setRazonSocial(microempresa.getRazonSocial());
        microempresaDTO.setNombreComercial(microempresa.getNombreComercial());
        microempresaDTO.setNit(microempresa.getNit());
        microempresaDTO.setDireccion(microempresa.getDireccion());
        microempresaDTO.setCiudad(microempresa.getCiudad());
        microempresaDTO.setTelefono(microempresa.getTelefono());
        microempresaDTO.setEmail(microempresa.getEmail());
        microempresaDTO.setEstado(microempresa.getEstado());
        microempresaDTO.setFechaRegistro(microempresa.getFechaRegistro());
        microempresaDTO.setIdPlan(microempresa.getPlan().getId());
        return microempresaDTO;
    }
}
