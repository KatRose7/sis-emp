package com.example.sistema.empresarial.sistema_empresarial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistema.empresarial.sistema_empresarial.dto.MicroempresaDTO;
import com.example.sistema.empresarial.sistema_empresarial.service.MicroempresaService;

@RestController
@RequestMapping("/api/microempresas")
public class MicroempresaController {

    private final MicroempresaService microempresaService;

    public MicroempresaController(MicroempresaService microempresaService) {
        this.microempresaService = microempresaService;
    }

    // Registrar una nueva microempresa
    @PostMapping
    public ResponseEntity<MicroempresaDTO> registrarMicroempresa(@RequestBody MicroempresaDTO microempresaDTO) {
        MicroempresaDTO nuevaMicroempresa = microempresaService.registrarMicroempresa(microempresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMicroempresa);
    }

    // Actualizar una microempresa existente
    @PutMapping("/{idTenant}")
    public ResponseEntity<MicroempresaDTO> actualizarMicroempresa(@PathVariable Long idTenant, @RequestBody MicroempresaDTO microempresaDTO) {
        MicroempresaDTO microempresaActualizada = microempresaService.actualizarMicroempresa(idTenant, microempresaDTO);
        if (microempresaActualizada != null) {
            return ResponseEntity.ok(microempresaActualizada);
        }
        return ResponseEntity.notFound().build();  // Si no se encuentra la microempresa
    }
}
