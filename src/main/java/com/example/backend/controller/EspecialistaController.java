package com.example.backend.controller;

import com.example.backend.model.Especialista;
import com.example.backend.service.EspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/especialistas")
public class EspecialistaController {

    @Autowired
    private EspecialistaService especialistaService;

    // Obtener todos los especialistas
    @GetMapping
    public ResponseEntity<List<Especialista>> getAllEspecialistas() {
        return ResponseEntity.ok(especialistaService.getAllEspecialistas());
    }

    // Obtener horarios de un especialista por ID
    @GetMapping("/{id}/horarios")
    public ResponseEntity<Map<String, List<String>>> getHorariosByEspecialistaId(@PathVariable String id) {
        return ResponseEntity.ok(especialistaService.getHorariosByEspecialistaId(id));
    }
}