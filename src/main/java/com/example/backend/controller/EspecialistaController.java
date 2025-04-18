package com.example.backend.controller;

import com.example.backend.dto.CitaDTO;
import com.example.backend.service.EspecialistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialistas")
public class EspecialistaController {

    private final EspecialistaService especialistaService;

    public EspecialistaController(EspecialistaService especialistaService) {
        this.especialistaService = especialistaService;
    }

    @GetMapping("/{id}/citas")
    public List<CitaDTO> obtenerCitas(@PathVariable String id) {
        return especialistaService.obtenerCitasPorEspecialista(id);
    }
}

